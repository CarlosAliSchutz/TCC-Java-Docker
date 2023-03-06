package br.com.cwi.api.controller.response;

import br.com.cwi.api.domain.enums.StatusAmizade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListarAmizadesResponse {

    private Long idDestinatario;
    private String nomeDestinatario;
    private String emailDestinatario;
    private String apelidoDestinatario;
    private String fotoDestinatario;

    private Long idRemetente;
    private String nomeRemetente;
    private String emailRemetente;
    private String apelidoRemetente;
    private String fotoRemetente;

    private StatusAmizade statusAmizade;
    }
