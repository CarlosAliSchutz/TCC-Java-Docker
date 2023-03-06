package br.com.cwi.api.controller.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MensagemResponse {
    private String response;
}
