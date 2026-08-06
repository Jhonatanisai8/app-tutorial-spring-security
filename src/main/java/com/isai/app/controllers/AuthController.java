package com.isai.app.controllers;

import com.isai.app.dto.req.UsuarioLoginRequestDTO;
import com.isai.app.dto.req.UsuarioRegistroRequestDTO;
import com.isai.app.dto.res.JWTResponseDTO;
import com.isai.app.security.JWTGenerator;
import com.isai.app.service.RolService;
import com.isai.app.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final RolService rolService;
    private final JWTGenerator jwtGenerator;

    @PostMapping("/login")
    public ResponseEntity<JWTResponseDTO> login(@RequestBody UsuarioLoginRequestDTO usuarioLoginRequestDTO) {
        return ResponseEntity.ok(usuarioService.login(usuarioLoginRequestDTO));
    }


    @PostMapping("/registro")
    public ResponseEntity<String> registro(@RequestBody UsuarioRegistroRequestDTO usuarioLoginRequestDTO) {
        usuarioService.registrar(usuarioLoginRequestDTO);
        return new ResponseEntity<>("Usuario Registrado", HttpStatus.CREATED);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(Authentication authentication) {
        String token = jwtGenerator.refreshToken(authentication);
        JWTResponseDTO jwtRefresh = new JWTResponseDTO(token);
        return new ResponseEntity<JWTResponseDTO>(jwtRefresh, HttpStatus.OK);
    }

}
