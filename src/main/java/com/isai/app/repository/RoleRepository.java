package com.isai.app.repository;

import com.isai.app.model.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository
        extends CrudRepository<Rol, Long> {

    Optional<Rol> findByNombre(String nombre);

}
