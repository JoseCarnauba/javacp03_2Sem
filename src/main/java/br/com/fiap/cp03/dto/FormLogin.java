package br.com.fiap.cp03.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class FormLogin {

    private String username;
    private String password;
    private Set<String> roles;
}
