package br.com.fiap.cp03.service;

import br.com.fiap.cp03.model.Aluno;
import br.com.fiap.cp03.model.Curso;
import br.com.fiap.cp03.repository.AlunoRepository;
import br.com.fiap.cp03.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository, CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
    }

    // Método para criar um novo Aluno
    public Aluno salvarAluno(String nome, String email, Set<Long> cursosId) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setEmail(email);

        // Associando os cursos ao aluno
        Set<Curso> cursos = cursosId.stream()
                .map(cursoId -> cursoRepository.findById(cursoId).orElse(null))  // Retorna null se o curso não for encontrado
                .filter(Objects::nonNull)  // Filtra os cursos nulos
                .collect(Collectors.toSet());

        aluno.setCursos(cursos); // Associando os cursos ao aluno
        return alunoRepository.save(aluno); // Salvando o aluno
    }

    // Método para buscar um Aluno pelo ID
    public Aluno buscarAluno(Long id) {
        return alunoRepository.findById(id).orElse(null); // Retorna null se o aluno não for encontrado
    }

    // Método para atualizar um Aluno existente
    public Aluno atualizarAluno(Long id, String nome, String email, Set<Long> cursosId) {
        Aluno alunoExistente = buscarAluno(id); // Verifica se o aluno existe

        if (alunoExistente == null) {
            return null; // Retorna null se o aluno não for encontrado
        }

        alunoExistente.setNome(nome);
        alunoExistente.setEmail(email);

        // Atualizando os cursos
        Set<Curso> cursos = cursosId.stream()
                .map(cursoId -> cursoRepository.findById(cursoId).orElse(null)) // Retorna null se o curso não for encontrado
                .filter(Objects::nonNull)  // Filtra os cursos nulos
                .collect(Collectors.toSet());

        alunoExistente.setCursos(cursos); // Atualizando a lista de cursos
        return alunoRepository.save(alunoExistente); // Salvando as alterações
    }

    // Método para deletar um Aluno pelo ID
    public void deletarAluno(Long id) {
        Aluno aluno = buscarAluno(id); // Verifica se o aluno existe
        if (aluno != null) {
            alunoRepository.delete(aluno); // Deleta o aluno
        }
    }

    // Método para listar todos os alunos
    public Set<Aluno> listarTodosAlunos() {
        List<Aluno> alunos = alunoRepository.findAll(); // Lista de Alunos
        return new HashSet<>(alunos);  // Converte para Set para eliminar duplicatas
    }

    public Aluno buscarAlunoPorId(Long alunoId) {
        return alunoRepository.findById(alunoId).orElse(null);
    }
}
