package com.isai.app.dto.req;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginRequestDTO {
    private String email;
    private String password;
}
