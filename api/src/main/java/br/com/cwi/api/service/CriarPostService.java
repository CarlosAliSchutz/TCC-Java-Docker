package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.PostRequest;
import br.com.cwi.api.controller.response.PostResponse;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.mapper.PostMapper;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.BuscarUsuarioSecuritySerivce;
import br.com.cwi.api.security.service.BuscarUsuarioService;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CriarPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BuscarUsuarioSecuritySerivce buscarUsuarioSecuritySerivce;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PostMapper postMapper;

    public PostResponse criar(PostRequest request) {

        Usuario autor = usuarioAutenticadoService.get();

        Post post = PostMapper.toEntity(request);
        post.setAutor(autor);
        post.setCriacao(LocalDate.now());

        postRepository.save(post);

        PostResponse response = PostMapper.toResponse(post);

        return response;
    }
}
