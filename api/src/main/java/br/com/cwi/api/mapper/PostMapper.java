package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.request.PostRequest;
import br.com.cwi.api.controller.response.PostResponse;
import br.com.cwi.api.domain.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostResponse map(Post post) {
        PostResponse result = new PostResponse();

        result.setIdPost(post.getId());
        result.setAutorApelido(post.getAutor().getApelido());
        result.setAutorNome(post.getAutor().getNome());
        result.setCriacao(post.getCriacao());
        result.setCurtidas(post.getCurtidasPost());
        result.setConteudo(post.getConteudo());
        result.setDisponivel(post.getDisponivel().name());
        result.setAutorFoto(post.getAutor().getImgPerfil());

        return result;
    }

    public static Post toEntity(PostRequest request) {
        return Post.builder()
                .conteudo(request.getConteudo())
                .disponivel(request.getDisponivel())
                .build();
    }

    public static PostResponse toResponse(Post entity) {


        return PostResponse.builder()
                .idPost(entity.getId())
                .conteudo(entity.getConteudo())
                .autorApelido(entity.getAutor().getApelido())
                .autorNome(entity.getAutor().getNome())
                .criacao(entity.getCriacao())
                .disponivel(entity.getDisponivel().name())
                .curtidas(entity.getCurtidasPost())
                .autorFoto(entity.getAutor().getImgPerfil())
                .build();
    }
}
