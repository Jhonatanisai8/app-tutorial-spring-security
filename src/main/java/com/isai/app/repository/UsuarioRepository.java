package com.isai.app.repository;

import com.isai.app.model.UsuarioEntidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository
        extends CrudRepository<UsuarioEntidad, Long> {

    Optional<UsuarioEntidad> findByUserName(String userName);

    boolean existsByEmail(String email);
}
