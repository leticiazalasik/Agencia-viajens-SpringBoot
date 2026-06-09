package com.example.agenciaviagens.services;

import com.example.agenciaviagens.entity.Usuario;
import com.example.agenciaviagens.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado"));

        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                List.of(
                        new SimpleGrantedAuthority("ROLE_" + usuario.getRole())
                )
        );
    }
}