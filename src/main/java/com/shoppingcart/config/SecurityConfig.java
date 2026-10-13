package com.shoppingcart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index", "/index.html", "/login",
                    "/error", "/css/**", "/js/**", "/images/**",
                    "/webjars/**").permitAll()
                .requestMatchers("/h2-console", "/h2-console/**")
                    .permitAll()
                .requestMatchers(HttpMethod.GET, "/products")
                    .permitAll()
                .requestMatchers("/admin", "/admin/**")
                    .hasRole("ADMIN")
                .requestMatchers("/products/add", "/products/edit/**",
                    "/products/delete/**", "/products/save")
                    .hasRole("ADMIN")
                .requestMatchers("/cart", "/cart/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/products", false)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/").permitAll()
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            );
        return http.build();
    }
    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.withUsername("admin")
            .password("{noop}admin").roles("ADMIN").build();
        UserDetails user = User.withUsername("user")
            .password("{noop}user").roles("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
    }
}