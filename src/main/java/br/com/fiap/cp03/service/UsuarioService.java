package br.com.fiap.cp03.service;

import br.com.fiap.cp03.dto.FormLogin;
import br.com.fiap.cp03.model.Usuario;
import br.com.fiap.cp03.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário pelo nome de usuário (ou e-mail)
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        // Retorna o UserDetails adaptado
        return usuario;
    }

    public Usuario registrarUsuario(@Valid FormLogin userForm) {
        // Verifica se o usuário já existe
        if (usuarioRepository.findByUsername(userForm.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Nome de usuário já existe");
        }

        // Cria uma nova instância de Usuario e define os atributos
        Usuario usuario = new Usuario();
        usuario.setUsername(userForm.getUsername());
        usuario.setPassword(passwordEncoder.encode(userForm.getPassword())); // Codifica a senha

        // Salva o novo usuário no banco de dados
        return usuarioRepository.save(usuario);
    }
}


