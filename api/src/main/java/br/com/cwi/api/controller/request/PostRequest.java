package br.com.cwi.api.controller.request;


import br.com.cwi.api.domain.enums.Disponivel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostRequest {

    @NotNull
    @NotEmpty
    private String conteudo;

    @NotNull
    private Disponivel disponivel;
}
