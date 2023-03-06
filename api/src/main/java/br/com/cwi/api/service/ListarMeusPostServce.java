package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.PostResponse;
import br.com.cwi.api.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarMeusPostServce {


    @Autowired
    private ListarMinhaTimeLineService listarMinhaTimeLineService;

    @Autowired
    private PostMapper postMapper;

    public List<PostResponse> listar() {


        return listarMinhaTimeLineService.listar().stream()
                .map(post -> postMapper.map(post)
                )
                .collect(Collectors.toList());
    }
}
