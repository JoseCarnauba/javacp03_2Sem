package br.com.fiap.cp03.config;

import br.com.fiap.cp03.model.Role;
import br.com.fiap.cp03.model.Usuario;
import br.com.fiap.cp03.repository.RoleRepository;
import br.com.fiap.cp03.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Verifica e cria a role ADMIN se não existir
            Optional<Role> adminRoleOpt = Optional.ofNullable(roleRepository.findByName("ROLE_ADMIN"));
            Role adminRole;
            if (adminRoleOpt.isEmpty()) {
                adminRole = new Role("ROLE_ADMIN", "Admin");
                roleRepository.save(adminRole);
            } else {
                adminRole = adminRoleOpt.get();
            }

            // Verifica e cria a role USER se não existir
            Optional<Role> userRoleOpt = Optional.ofNullable(roleRepository.findByName("ROLE_USER"));
            Role userRole;
            if (userRoleOpt.isEmpty()) {
                userRole = new Role("ROLE_USER", "User");
                roleRepository.save(userRole);
            } else {
                userRole = userRoleOpt.get();
            }

            // Verifica e cria o usuário admin se não existir
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRoles(Set.of(adminRole));
                usuarioRepository.save(admin);
            }

            // Verifica e cria o usuário user1 se não existir
            if (usuarioRepository.findByUsername("user1").isEmpty()) {
                Usuario user = new Usuario();
                user.setUsername("user1");
                user.setPassword(passwordEncoder.encode("password"));
                user.setRoles(Set.of(userRole));
                usuarioRepository.save(user);
            }
        };
    }
}
