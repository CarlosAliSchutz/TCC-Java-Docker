package br.com.cwi.api.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ComentarPostRequest {

    @NotEmpty
    private String comentario;
}
