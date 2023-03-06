package br.com.cwi.api.security.mapper;

import br.com.cwi.api.security.controller.request.UsuarioRequest;
import br.com.cwi.api.security.controller.response.UsuarioResponse;
import br.com.cwi.api.security.domain.Permissao;
import br.com.cwi.api.security.domain.Usuario;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setApelido(request.getApelido());
        entity.setDataNascimento(request.getDataNascimento());
        entity.setImgPerfil(request.getImgPerfil());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {
        return UsuarioResponse.builder()
                .idUsuario(entity.getId())
                .nome(entity.getNome())
                .apelido(entity.getApelido())
                .email(entity.getEmail())
                .dataNascimento(entity.getDataNascimento())
                .imgPerfil(entity.getImgPerfil())
                .permissoes(buildPermissoesResponse(entity.getPermissoes()))
                .build();
    }

    private static List<String> buildPermissoesResponse(List<Permissao> permissoes) {
        return permissoes.stream()
                .map(Permissao::getNome)
                .collect(toList());
    }
}
