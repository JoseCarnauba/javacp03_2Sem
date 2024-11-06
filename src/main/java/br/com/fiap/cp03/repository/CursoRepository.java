package br.com.fiap.cp03.repository;

import br.com.fiap.cp03.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository {
    List<Curso> findByAlunos_Id(Long alunoId);
}
