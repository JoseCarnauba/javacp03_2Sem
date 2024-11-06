package br.com.fiap.cp03.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    private LocalDate dataMatricula;

    private String status;

    // Construtor com argumentos (opcional, se precisar dele)
    public Matricula(Aluno aluno, Curso curso, LocalDate dataMatricula, String status) {
        this.aluno = aluno;
        this.curso = curso;
        this.dataMatricula = dataMatricula;
        this.status = status;
    }

}
