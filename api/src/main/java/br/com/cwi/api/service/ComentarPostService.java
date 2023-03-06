package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.ComentarPostRequest;
import br.com.cwi.api.controller.response.MensagemResponse;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.repository.ComentarioRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarPostService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarPostService buscarPostService;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public MensagemResponse comentar(Long idPost, ComentarPostRequest request) {

        Post post = buscarPostService.findByIdWithException(idPost);
        Usuario usuario = usuarioAutenticadoService.get();

        Comentario comentario = Comentario.builder()
                .usuario(usuario)
                .comentario(request.getComentario())
                .build();

        post.getComentarios().add(comentario);

        comentario.setPost(post);

        comentarioRepository.save(comentario);

        return MensagemResponse.builder()
                .response("Você comentou no post de " + post.getAutor().getApelido() +": " + comentario.getComentario())
                .build();
    }
}
