package br.com.fiap.cp03.dto;

import br.com.fiap.cp03.model.Aluno;
import java.util.List;
import java.util.stream.Collectors;

public record AlunoDto(
        Long id,
        String nome,
        String email,
        List<CursoDto> cursosMatriculados
) {
    // Método estático para converter a entidade Aluno em AlunoDto
    public static AlunoDto fromAluno(Aluno aluno) {
        // Converte a lista de cursos do aluno para CursoDto
        List<CursoDto> cursosDto = aluno.getCursos().stream()
                .map(CursoDto::fromCurso) // Usando o método fromCurso para converter Curso para CursoDto
                .collect(Collectors.toList());

        // Retorna um novo AlunoDto com os dados do aluno
        return new AlunoDto(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                cursosDto
        );
    }
}
