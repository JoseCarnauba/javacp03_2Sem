package br.com.fiap.cp03.dto;

import java.time.LocalDate;

public record CursoDto(
        Long id,
        String nome,
        String descricao,
        LocalDate dataInicio,
        int cargaHoraria
) {
}
