package com.shoppingcart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Autorizar peticiones
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll() // Permite el acceso libre a la consola H2
                .anyRequest().authenticated()                  // El resto requiere autenticación
            )
            // 2. Desactivar CSRF para H2 (H2 no envía tokens CSRF en sus peticiones AJAX/POST)
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )
            // 3. Permitir el uso de iFrames para el mismo dominio (H2 usa frames internamente)
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            )
            // 4. Formulario de login por defecto
            .formLogin(form -> form
                .defaultSuccessUrl("/products", true) // Redirige a /products tras loguearte
                .permitAll()
            );

        return http.build();
    }
}
