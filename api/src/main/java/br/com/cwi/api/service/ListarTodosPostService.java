package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.PostResponse;
import br.com.cwi.api.mapper.PostMapper;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ListarTodosPostService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private GetAmigosService getAmigosService;


    public List<PostResponse> listarTodos() {
        List<PostResponse> posts = new ArrayList<>();
        Usuario usuario = usuarioAutenticadoService.get();

        usuario.getPosts().stream().forEach(post -> posts.add(postMapper.toResponse(post)));

        getAmigosService.get().stream().forEach(remetente -> {
            remetente.getPosts().stream().forEach(post -> posts.add(postMapper.toResponse(post)));
        });

        return posts.stream()
                .sorted((post1,post2) -> post2.getCriacao().compareTo(post1.getCriacao()))
                .collect(Collectors.toList());

    }
}
