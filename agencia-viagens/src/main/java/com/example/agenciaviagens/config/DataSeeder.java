package com.example.agenciaviagens.config;

import com.example.agenciaviagens.entity.Usuario;
import com.example.agenciaviagens.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedUsers(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (usuarioRepository.findByEmail("leticia@agencia.com").isEmpty()) {

                Usuario admin = new Usuario();
                admin.setNome("Leticia");
                admin.setEmail("leticia@agencia.com");
                admin.setSenha(passwordEncoder.encode("123456"));
                admin.setRole("ADMIN");

                usuarioRepository.save(admin);
            }

            if (usuarioRepository.findByEmail("ana@agencia.com").isEmpty()) {

                Usuario user = new Usuario();
                user.setNome("Ana");
                user.setEmail("ana@agencia.com");
                user.setSenha(passwordEncoder.encode("123456"));
                user.setRole("USER");

                usuarioRepository.save(user);
            }
        };
    }
}