package br.com.fiap.cp03.dto;

import java.util.List;

public record AlunoDto(
        Long id,
        String nome,
        String email,
        List<CursoDto> cursosMatriculados
) {
}
