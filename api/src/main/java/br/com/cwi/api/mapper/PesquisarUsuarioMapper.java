package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.ListarAmizadesResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PesquisarUsuarioMapper {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public static ListarAmizadesResponse toResponse(Amizade entity) {

        return ListarAmizadesResponse.builder()
                .idDestinatario(entity.getDestinatario().getId())
                .idRemetente(entity.getRemetente().getId())
                .nomeDestinatario(entity.getDestinatario().getNome())
                .nomeRemetente(entity.getRemetente().getNome())
                .emailDestinatario(entity.getDestinatario().getEmail())
                .emailRemetente(entity.getRemetente().getEmail())
                .apelidoDestinatario(entity.getDestinatario().getApelido())
                .apelidoRemetente(entity.getRemetente().getApelido())
                .fotoDestinatario(entity.getDestinatario().getImgPerfil())
                .fotoRemetente(entity.getRemetente().getImgPerfil())
                .statusAmizade(entity.getStatus())
                .build();
    }
}

