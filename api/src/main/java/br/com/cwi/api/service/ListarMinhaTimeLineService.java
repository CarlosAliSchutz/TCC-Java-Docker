package br.com.cwi.api.service;

import br.com.cwi.api.domain.Post;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ListarMinhaTimeLineService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ListarAmizadesService listarAmizadesService;


    public List<Post> listar() {
        Usuario autor = usuarioAutenticadoService.get();

        List<Post> meusPosts = postRepository.findAllByAutor(autor);
        List<Usuario> amizades = listarAmizadesService.listarAmigos();

        List<Post> postAmigos = amizades.stream()
                .map(amizade -> postRepository.findAllByAutor(amizade))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<Post> posts = Stream.concat(meusPosts.stream(), postAmigos.stream().collect(Collectors.toList()).stream())
                .sorted((post1, post2) -> post1.getCriacao().compareTo(post2.getCriacao()))
                .collect(Collectors.toList());

        Collections.reverse(posts);

        return posts;
    }


}
