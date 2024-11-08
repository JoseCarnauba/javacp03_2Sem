package br.com.fiap.cp03.dto;

import br.com.fiap.cp03.model.Curso;
import java.time.LocalDate;

public record CursoDto(
        Long id,
        String nome,
        String descricao,
        LocalDate dataInicio,
        int cargaHoraria
) {
    // Método estático para converter a entidade Curso em CursoDto
    public static CursoDto fromCurso(Curso curso) {
        return new CursoDto(
                curso.getId(),
                curso.getNome(),
                curso.getDescricao(),
                curso.getDataInicio(),
                curso.getCargaHoraria()
        );
    }
}
