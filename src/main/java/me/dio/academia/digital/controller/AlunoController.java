package me.dio.academia.digital.controller;

import me.dio.academia.digital.dto.MessageResponseDTO;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.exceptions.AlunoNotFound;
import me.dio.academia.digital.service.IAlunoService;
import me.dio.academia.digital.service.impl.AlunoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoServiceImpl alunoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> getAll(@RequestParam(value = "dataDeNascimento", required = false) String  dataDeNascimento){
        return alunoService.getAll(dataDeNascimento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluno create(@Valid @RequestBody AlunoForm form){
        return alunoService.create(form);
    }

    @GetMapping("/avaliacoes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(@PathVariable Long id) throws AlunoNotFound {
        return alunoService.getAllAvaliacaoFisica(id);
    }

    @GetMapping("/avaliacoes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Aluno findById(@PathVariable @Valid Long id) throws AlunoNotFound {
        return alunoService.get(id);
    }

    @DeleteMapping("/avaliacoes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String delete(@PathVariable Long id) throws AlunoNotFound {
        return alunoService.delete(id);
    }

    @PutMapping("/avaliacoes/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO update(@PathVariable Long id, @RequestBody @Valid AlunoUpdateForm form) throws AlunoNotFound {return alunoService.update(id, form);}
}
