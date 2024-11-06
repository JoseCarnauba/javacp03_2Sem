package br.com.fiap.cp03.dto;

import java.time.LocalDate;

public record MatriculaDto(
        Long id,
        Long alunoId,
        Long cursoId,
        LocalDate dataMatricula,
        String status
) {
}
