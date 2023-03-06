package br.com.cwi.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostResponse {

    private Long idPost;
    private String autorApelido;
    private String autorNome;
    private String autorFoto;
    private String conteudo;
    private LocalDate criacao;
    private String disponivel;
    private Integer curtidas;
}
