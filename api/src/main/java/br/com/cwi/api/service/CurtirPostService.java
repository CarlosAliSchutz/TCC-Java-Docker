package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.MensagemResponse;
import br.com.cwi.api.domain.Curtida;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.repository.CurtidaRepository;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CurtirPostService {

    public static final int CURTIDA = 1;
    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarPostService buscarPostService;

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private PostRepository postRepository;

    public MensagemResponse curtir(Long idPost) {

        Post post = buscarPostService.findByIdWithException(idPost);
        Usuario usuario = usuarioAutenticadoService.get();

        List<Curtida> curtidas = curtidaRepository.findAll();

        if (curtidas.isEmpty()) {
            Curtida curtida = Curtida.builder()
                    .usuario(usuario)
                    .build();
            post.getCurtidas().add(curtida);
            post.setCurtidasPost(post.getCurtidas().toArray().length);
            postRepository.save(post);
            curtida.setPost(post);
            curtidaRepository.save(curtida);

            return MensagemResponse.builder()
                    .response("Você curtiu post de " + post.getAutor().getApelido())
                    .build();
        } else {
            Curtida curtida = curtidas.get(0);
            post.setCurtidasPost(post.getCurtidas().toArray().length - CURTIDA);
            postRepository.save(post);
            curtidaRepository.delete(curtida);
            return MensagemResponse.builder()
                    .response("Você descurtiu post de " + post.getAutor().getApelido())
                    .build();
        }

    }
}