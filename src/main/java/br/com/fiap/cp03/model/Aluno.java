package br.com.fiap.cp03.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "T_ALUNO")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode ser vazio ou conter apenas espaços.")
    private String nome;

    @Email(message = "E-mail deve ser válido.")
    @NotBlank(message = "O e-mail não pode ser vazio.")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "matriculas",  // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "aluno_id"),  // Coluna da chave estrangeira para a tabela 'Aluno'
            inverseJoinColumns = @JoinColumn(name = "curso_id")  // Coluna da chave estrangeira para a tabela 'Curso'
    )
    private Set<Curso> cursos = new HashSet<>();  // Coleção de cursos associados ao aluno

    // Método para adicionar cursos à lista de cursos
    public void adicionaCurso(Curso curso) {
        this.cursos.add(curso);
    }

    // Sobrescrita dos métodos equals e hashCode com base no ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aluno aluno = (Aluno) obj;
        return id != null && id.equals(aluno.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
