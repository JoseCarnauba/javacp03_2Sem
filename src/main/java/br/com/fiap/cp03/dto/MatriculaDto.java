package br.com.fiap.cp03.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

public record MatriculaDto(
        Long id,
        Long alunoId,
        Long cursoId,
        LocalDate dataMatricula,
        String status
) {
}
