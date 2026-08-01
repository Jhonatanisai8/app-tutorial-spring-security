package com.isai.app.dto;

import com.isai.app.model.Rol;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String userName;
    private String password;
    private String email;
    private Set<Rol> roles;
}
