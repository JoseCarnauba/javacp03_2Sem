package br.com.fiap.cp03.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "T_CURSO")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "carga_horaria", nullable = false)
    private int cargaHoraria;

    @ManyToMany(mappedBy = "cursos")  // Relacionamento bidirecional: mapeia a propriedade 'cursos' de Aluno
    private Set<Aluno> alunos = new HashSet<>();  // Coleção de alunos associados a este curso

    // Sobrescrita dos métodos equals e hashCode com base no ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Curso curso = (Curso) obj;
        return id != null && id.equals(curso.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
