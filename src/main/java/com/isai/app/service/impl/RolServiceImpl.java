package com.isai.app.service.impl;

import com.isai.app.model.Rol;
import com.isai.app.repository.RoleRepository;
import com.isai.app.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolServiceImpl
        implements RolService {

    private final RoleRepository rolRepository;

    @Override
    public Optional<Rol> findByName(String nombre) {
        return rolRepository.findByNombre(nombre);
    }
}
