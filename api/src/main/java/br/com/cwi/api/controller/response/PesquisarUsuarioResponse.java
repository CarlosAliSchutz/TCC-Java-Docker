package br.com.cwi.api.controller.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PesquisarUsuarioResponse {

    private String nome;
    private String email;
}
