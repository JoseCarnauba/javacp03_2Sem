package br.com.fiap.cp03.repository;

import br.com.fiap.cp03.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository {
    List<Aluno> findByCursos_Id(Long cursoId);
}
