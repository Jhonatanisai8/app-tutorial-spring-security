package com.isai.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerficadorController {
    @RequestMapping(path = "/token")
    public String token() {
        return "Hola si funciona, el token de acceso esta bien";
    }

    @GetMapping(path = "/admin")
    public String admin() {
        return "Hola Admin si funciona, el token de acceso esta bien";
    }

    @GetMapping(path = "/usuario")
    public String usuario() {
        return "Hola Usuario si funciona, el token de acceso esta bien";
    }

}
