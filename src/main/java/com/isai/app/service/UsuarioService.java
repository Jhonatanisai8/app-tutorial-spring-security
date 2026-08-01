package com.isai.app.service;

import com.isai.app.dto.UsuarioDTO;
import com.isai.app.dto.req.UsuarioLoginRequestDTO;
import com.isai.app.dto.req.UsuarioRegistroRequestDTO;
import com.isai.app.dto.res.JWTResponseDTO;

public interface UsuarioService {

    UsuarioDTO registrar(UsuarioRegistroRequestDTO registroRequestDTO);

    JWTResponseDTO login(UsuarioLoginRequestDTO loginRequestDTO);

}
