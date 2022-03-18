package me.dio.academia.digital.dto;

import lombok.Builder;
import lombok.Data;
import me.dio.academia.digital.entity.Matricula;

@Data
@Builder
public class MessageResponseDTO {
    private String message;
}
