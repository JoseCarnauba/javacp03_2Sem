package br.com.fiap.cp03.repository;

import br.com.fiap.cp03.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository <Role, Long> {
    Role findByName(String roleName);
}
