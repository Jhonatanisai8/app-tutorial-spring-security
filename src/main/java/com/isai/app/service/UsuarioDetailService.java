package com.isai.app.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioDetailService {
    UserDetails loadUserByUsername(String username);
}
