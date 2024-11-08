package br.com.fiap.cp03.controller;

import br.com.fiap.cp03.dto.FormLogin;
import br.com.fiap.cp03.model.Usuario;
import br.com.fiap.cp03.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {

        private final UsuarioService usuarioService;

        public UsuarioController(UsuarioService usuarioService)
        {
            this.usuarioService = usuarioService;
        }

        @PostMapping("/cadastrar")
        public ResponseEntity<Usuario> registrarUsuario(@RequestBody @Valid FormLogin userForm) {
            // Converte o UserForm em uma entidade User e salva
            Usuario usuario = usuarioService.registrarUsuario(userForm);
            return ResponseEntity.ok(usuario);
        }
    }

