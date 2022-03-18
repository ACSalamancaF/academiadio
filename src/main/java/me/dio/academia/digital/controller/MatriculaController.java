package me.dio.academia.digital.controller;

import me.dio.academia.digital.dto.MessageResponseDTO;
import me.dio.academia.digital.entity.Matricula;
import me.dio.academia.digital.entity.form.MatriculaForm;
import me.dio.academia.digital.exceptions.AlunoNotFound;
import me.dio.academia.digital.service.impl.MatriculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaServiceImpl matriculaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO create(@RequestBody @Valid MatriculaForm form) throws AlunoNotFound { return matriculaService.create(form);}

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Matricula> getdAll(@RequestParam(value = "bairro", required = false) String bairro){
        return matriculaService.getAll(bairro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        matriculaService.delete(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Matricula findById(@PathVariable Long id){
        return matriculaService.get(id);
    }
}
