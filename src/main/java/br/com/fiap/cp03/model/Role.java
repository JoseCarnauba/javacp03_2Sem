package br.com.fiap.cp03.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "T_ROLE")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_role", nullable = false, unique = true)
    private String name;

    @Column(name = "ds_label", nullable = false)
    private String label;

    // Construtor padrão
    public Role() {}

    // Construtor adicional para simplificar a criação
    public Role(String name, String label) {
        this.name = name;
        this.label = label;
    }
}
