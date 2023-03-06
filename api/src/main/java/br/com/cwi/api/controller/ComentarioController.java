package br.com.cwi.api.controller;

import br.com.cwi.api.controller.request.ComentarPostRequest;
import br.com.cwi.api.controller.response.MensagemResponse;
import br.com.cwi.api.service.ComentarPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private ComentarPostService comentarPostService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/{idPost}")
    public MensagemResponse comentar(@PathVariable Long idPost, @Valid @RequestBody ComentarPostRequest request){
        return comentarPostService.comentar(idPost, request);
    }
}
