package com.isai.app.service.impl;

import com.isai.app.dto.UsuarioDTO;
import com.isai.app.dto.req.UsuarioLoginRequestDTO;
import com.isai.app.dto.req.UsuarioRegistroRequestDTO;
import com.isai.app.dto.res.JWTResponseDTO;
import com.isai.app.exceptions.personalization.ConflicException;
import com.isai.app.exceptions.personalization.NotFoundException;
import com.isai.app.model.Rol;
import com.isai.app.model.UsuarioEntidad;
import com.isai.app.repository.UsuarioRepository;
import com.isai.app.security.JWTGenerator;
import com.isai.app.service.RolService;
import com.isai.app.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl
        implements UsuarioService {


    private final UsuarioRepository usuarioRepository;

    private final RolService rolService;

    private final AuthenticationManager authenticationManager;

    private final JWTGenerator jwtGenerator;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioDTO registrar(UsuarioRegistroRequestDTO registroRequestDTO) {
        if (usuarioRepository.existsByEmail(registroRequestDTO.getEmail())) {
            throw new ConflicException("El correo ya existe");
        }

        Rol rol = rolService.findByName("USER")
                .orElseThrow(() -> new NotFoundException("Rol no encontrado"));
        Set<Rol> roles = new HashSet<>();
        roles.add(rol);


        UsuarioEntidad usuarioEntidad = UsuarioEntidad.builder()
                .userName(registroRequestDTO.getUserName())
                .password(passwordEncoder.encode(registroRequestDTO.getPassword()))
                .email(registroRequestDTO.getEmail())
                .roles(roles)
                .build();
        usuarioRepository.save(usuarioEntidad);

        UsuarioDTO usuarioDTO = usuarioEntidadTousuarioDTO(usuarioEntidad);

        return usuarioDTO;
    }

    private static UsuarioDTO usuarioEntidadTousuarioDTO(UsuarioEntidad usuarioEntidad) {
        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .userName(usuarioEntidad.getUserName())
                .password(usuarioEntidad.getPassword())
                .email(usuarioEntidad.getEmail())
                .roles(usuarioEntidad.getRoles())
                .build();
        return usuarioDTO;
    }

    @Override
    public JWTResponseDTO login(UsuarioLoginRequestDTO loginRequestDTO) {

    }
}
