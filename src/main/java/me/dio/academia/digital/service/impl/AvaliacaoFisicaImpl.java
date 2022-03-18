package me.dio.academia.digital.service.impl;

import me.dio.academia.digital.dto.MessageResponseDTO;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import me.dio.academia.digital.exceptions.AlunoNotFound;
import me.dio.academia.digital.repository.AlunoRepository;
import me.dio.academia.digital.repository.AvaliacaoFisicaRepository;
import me.dio.academia.digital.service.IAvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoFisicaImpl implements IAvaliacaoFisicaService {

    @Autowired
    public AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    @Autowired
    public AlunoRepository alunoRepository;

    private MessageResponseDTO createMessageResponse(String s, Long id2) {
        return MessageResponseDTO.builder()
                .message(s + id2)
                .build();
    }

    @Override
    public MessageResponseDTO create(AvaliacaoFisicaForm form) throws AlunoNotFound {
        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();

        Aluno aluno = alunoRepository.findById(form.getAlunoId())
                .orElseThrow(()-> new AlunoNotFound(form.getAlunoId()));

        avaliacaoFisica.setAluno(aluno);
        avaliacaoFisica.setPeso(form.getPeso());
        avaliacaoFisica.setAltura(form.getAltura());
        AvaliacaoFisica savedAvaliacao = avaliacaoFisicaRepository.save(avaliacaoFisica);

        MessageResponseDTO messageResponse = createMessageResponse("Matrícula successfully saved with ID ", savedAvaliacao.getId());

        return messageResponse;
    }

    @Override
    public AvaliacaoFisica get(Long id) {
        return null;
    }

    @Override
    public List<AvaliacaoFisica> getAll() {
        return null;
    }

    @Override
    public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
        return null;
    }

    @Override
    public void delete(Long id) {
        avaliacaoFisicaRepository.deleteById(id);
    }

}
