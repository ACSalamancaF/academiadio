package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.dto.MessageResponseDTO;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.exceptions.AlunoNotFound;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.MatriculaRepository;
import me.dio.academia.digital.service.IMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaServiceImpl implements IMatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    private MessageResponseDTO createMessageResponse(String s, Long id2) {
        return MessageResponseDTO.builder()
                .message(s + id2)
                .build();
    }

    public MessageResponseDTO create(MatriculaForm form) throws AlunoNotFound {
        Matricula matricula = new Matricula();
        Aluno aluno = alunoRepository.findById(form.getAlunoId())
                .orElseThrow(()-> new AlunoNotFound(form.getAlunoId()));
        matricula.setAluno(aluno);
        Matricula saved = matriculaRepository.save(matricula);

        MessageResponseDTO messageResponse = createMessageResponse("Matrícula successfully saved with ID ", saved.getId());

        return messageResponse;
    }

    @Override
    public Matricula get(Long id) {
        Matricula matricula = matriculaRepository.getById(id);
        return  matricula;
    }

    public List<Matricula> getAll(String bairro) {
        if(bairro==null) {
            return matriculaRepository.findAll();
        }else{
            return matriculaRepository.findByAlunoBairro(bairro);
        }
     }

    @Override
    public void delete(Long id) {
        matriculaRepository.deleteById(id);

    }
}
