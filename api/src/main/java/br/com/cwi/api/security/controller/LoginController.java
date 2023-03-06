package br.com.cwi.api.security.controller;

import br.com.cwi.api.security.controller.response.UsuarioResponse;
import br.com.cwi.api.security.service.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @PostMapping
    public UsuarioResponse login() {
        return buscarUsuarioService.buscar();
    }
}
