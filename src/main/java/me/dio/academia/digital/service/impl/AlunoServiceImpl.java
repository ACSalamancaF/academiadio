package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.dto.MessageResponseDTO;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.exceptions.AlunoNotFound;
import me.dio.academia.digital.infra.utils.JavaTimeUtils;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.service.IAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlunoServiceImpl implements IAlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Aluno create(AlunoForm form) {
        Aluno aluno = new Aluno();
        aluno.setNome(form.getNome());
        aluno.setCpf(form.getCpf());
        aluno.setBairro(form.getBairro());
        aluno.setDataDeNascimento(form.getDataDeNascimento());

        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno get(Long id) throws AlunoNotFound {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(()-> new AlunoNotFound(id));

        return aluno;

    }

    @Override
    public List<Aluno> getAll(String dataDeNascimento) {
        if(dataDeNascimento==null) {
            return alunoRepository.findAll();
        }else{
            LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
            return alunoRepository.findByDataDeNascimento(localDate);
        }
    }

    @Override
    public MessageResponseDTO update(Long id, AlunoUpdateForm formUpdate) throws AlunoNotFound {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new AlunoNotFound(id));
        AlunoUpdateForm alunoUpdateForm = new AlunoUpdateForm(formUpdate.getNome(), formUpdate.getBairro(), formUpdate.getDataDeNascimento());

        MessageResponseDTO messageResponse = createMessageResponse("Successfully update with name ", id);
        return messageResponse;
    }

    private MessageResponseDTO createMessageResponse(String s, Long id2) {
        return MessageResponseDTO.builder()
                .message(s + id2)
                .build();
    }

    @Override
    public String delete(Long id) throws AlunoNotFound {

        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(()-> new AlunoNotFound(id));

        alunoRepository.delete(aluno);

        return "Deletable";
    }

    @Override
    public List<AvaliacaoFisica> getAllAvaliacaoFisica(Long id) throws AlunoNotFound {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(()-> new AlunoNotFound(id));

        return aluno.getAvaliacoes();
    }

}
