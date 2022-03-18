package me.dio.academia.digital.controller;

import me.dio.academia.digital.dto.MessageResponseDTO;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AvaliacaoFisicaForm;
import me.dio.academia.digital.exceptions.AlunoNotFound;
import me.dio.academia.digital.service.impl.AvaliacaoFisicaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    @Autowired
    public AvaliacaoFisicaImpl avaliacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO create(@RequestBody @Valid AvaliacaoFisicaForm form) throws AlunoNotFound {
        return avaliacaoService.create(form);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        avaliacaoService.delete(id);
    }

}
