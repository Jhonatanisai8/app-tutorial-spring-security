package com.isai.app.dto.req;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistroRequestDTO {
    private String userName;

    private String password;

    private String email;

}
