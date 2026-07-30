package com.isai.app.exceptions.general;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorObject {

    private Integer codigoEstado;

    private String mensaje;

    private Date timestamp;

}
