package me.dio.academia.digital.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlunoNotFound extends Exception{

    public AlunoNotFound(Long id){
        super(String.format("Aluno with ID %s not found!", id));
    }

}
