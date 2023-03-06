package br.com.cwi.api.security.controller.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioResponse {

    private Long idUsuario;
    private String nome;
    private String email;
    private String apelido;
    private LocalDate dataNascimento;
    private String imgPerfil;
    private List<String> permissoes;
}
