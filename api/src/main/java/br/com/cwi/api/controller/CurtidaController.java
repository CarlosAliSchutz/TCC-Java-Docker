package br.com.cwi.api.controller;

import br.com.cwi.api.controller.response.MensagemResponse;
import br.com.cwi.api.service.CurtirPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curtir")
public class CurtidaController {

    @Autowired
    private CurtirPostService curtirPostService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/{idPost}")
    public MensagemResponse curtir(@PathVariable Long idPost){
        return curtirPostService.curtir(idPost);
    }
}
