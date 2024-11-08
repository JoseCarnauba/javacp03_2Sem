package br.com.fiap.cp03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**", "/login", "/webjars/**", "/css/**").permitAll() // Acesso público para login e recursos estáticos
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Restringe o acesso para ADMIN nas rotas /admin
                        .requestMatchers("/aluno/**").hasRole("ALUNO") // Restringe o acesso para ALUNO nas rotas /aluno
                        .anyRequest().authenticated() // Exige autenticação para qualquer outra rota
                )
                .formLogin(form -> form
                        .loginPage("/login") // Página de login personalizada
                        .defaultSuccessUrl("/home", true) // Página de redirecionamento após sucesso no login
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL de logout
                        .logoutSuccessUrl("/login?logout") // Redirecionamento após logout
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return null;
    }
}
