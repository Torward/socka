package ru.lomov.socka.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/auth/register", "/auth/login").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/users/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/posts/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/messages/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/comments/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/notifies/**").hasAnyRole("ADMIN", "USER")

                .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/auth/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }
}