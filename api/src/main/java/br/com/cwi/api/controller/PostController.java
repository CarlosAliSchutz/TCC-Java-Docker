package br.com.cwi.api.controller;

import br.com.cwi.api.controller.request.PostRequest;
import br.com.cwi.api.controller.response.PostResponse;
import br.com.cwi.api.service.CriarPostService;
import br.com.cwi.api.service.ListarMeusPostServce;
import br.com.cwi.api.service.ListarTodosPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private CriarPostService criarPostService;

    @Autowired
    private ListarMeusPostServce listarMeusPostServce;

    @Autowired
    private ListarTodosPostService listarTodosPostService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/criarPost")
    public PostResponse criar(@Valid @RequestBody PostRequest request) {
        return criarPostService.criar(request);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<PostResponse> listarMeuFeed() {
        return listarMeusPostServce.listar();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/listarTodos")
    public List<PostResponse> listarTodos(){
        return listarTodosPostService.listarTodos();
    }

}
