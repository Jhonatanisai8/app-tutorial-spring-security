package com.isai.app.service.impl;

import com.isai.app.model.Rol;
import com.isai.app.model.UsuarioEntidad;
import com.isai.app.repository.UsuarioRepository;
import com.isai.app.service.UsuarioDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl
        implements UsuarioDetailService {


    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        UsuarioEntidad usuarioEntidad = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        ArrayList<GrantedAuthority> roles = new ArrayList<>();
        for (Rol rol : usuarioEntidad.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        return new User(
                usuarioEntidad.getEmail(),
                usuarioEntidad.getPassword(),
                roles);
    }
}
