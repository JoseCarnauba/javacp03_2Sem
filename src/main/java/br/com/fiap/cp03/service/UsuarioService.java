package br.com.fiap.cp03.service;

import br.com.fiap.cp03.dto.FormLogin;
import br.com.fiap.cp03.model.Role;
import br.com.fiap.cp03.model.Usuario;
import br.com.fiap.cp03.repository.RoleRepository;
import br.com.fiap.cp03.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário pelo nome de usuário (ou e-mail)
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // Verifique se `Usuario` já implementa UserDetails:
        return usuario;

        /* Alternativa: Se `Usuario` NÃO implementar `UserDetails`, use o código abaixo
        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRoles().stream().map(Role::getName).toArray(String[]::new)) // Converte as roles para strings
                .build();
        */
    }

    // Método para registrar um novo usuário
    public Usuario registrarUsuario(@Valid FormLogin userForm) {
        // Verifica se o usuário já existe
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUsername(userForm.getUsername());
        if (usuarioExistente.isPresent()) {
            throw new IllegalArgumentException("Nome de usuário já existe");
        }

        // Cria uma nova instância de Usuario e define os atributos
        Usuario usuario = new Usuario();
        usuario.setUsername(userForm.getUsername());
        usuario.setPassword(passwordEncoder.encode(userForm.getPassword())); // Codifica a senha

        // Salva o novo usuário no banco de dados
        return usuarioRepository.save(usuario);
    }

    // Método para criar um usuário com roles
    public Usuario save(String username, String password, Set<String> roles) {
        // Cria um Set de Roles com base no nome das roles fornecidas
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : roles) {
            Role role = roleRepository.findByName(roleName);
            if (role != null) {
                roleSet.add(role);
            } else {
                throw new IllegalArgumentException("Role não encontrada: " + roleName);
            }
        }

        // Cria um novo usuário com as roles
        Usuario usuario = new Usuario(username, passwordEncoder.encode(password), roleSet);

        // Salva o usuário no banco de dados
        return usuarioRepository.save(usuario);
    }

    // Método para atualizar um usuário e suas roles
    public Usuario atualizarUsuario(Long usuarioId, String password, Set<String> roles) {
        Usuario usuarioExistente = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Atualiza a senha se fornecida
        if (password != null && !password.isEmpty()) {
            usuarioExistente.setPassword(passwordEncoder.encode(password));
        }

        // Atualiza as roles
        Set<Role> roleSet = new HashSet<>();
        for (String roleName : roles) {
            Role role = roleRepository.findByName(roleName);
            if (role != null) {
                roleSet.add(role);
            } else {
                throw new IllegalArgumentException("Role não encontrada: " + roleName);
            }
        }
        usuarioExistente.setRoles(roleSet);

        // Salva o usuário atualizado
        return usuarioRepository.save(usuarioExistente);
    }

    // Método para deletar um usuário
    public void deletarUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }
}
