package com.isai.app.service;

import com.isai.app.model.Rol;

import java.util.Optional;

public interface RolService {
    Optional<Rol> findByName(String nombre);
}
