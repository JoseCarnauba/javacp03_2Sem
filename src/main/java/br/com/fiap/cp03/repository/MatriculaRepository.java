package br.com.fiap.cp03.repository;

import br.com.fiap.cp03.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}
