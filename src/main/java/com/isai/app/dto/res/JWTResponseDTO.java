package com.isai.app.dto.res;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponseDTO {
    private String accesToken;
}
