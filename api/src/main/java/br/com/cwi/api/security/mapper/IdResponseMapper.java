package br.com.cwi.api.security.mapper;

import br.com.cwi.api.security.controller.response.IdResponse;

public class IdResponseMapper {

    public static IdResponse toResponse(Long id) {
        return IdResponse.builder().id(id).build();
    }
}
