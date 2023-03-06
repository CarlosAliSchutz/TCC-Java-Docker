package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.ListarAmizadesResponse;
import br.com.cwi.api.domain.enums.StatusAmizade;
import br.com.cwi.api.mapper.PesquisarUsuarioMapper;
import br.com.cwi.api.repository.AmizadeRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PesquisarUsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<ListarAmizadesResponse> pesquisar(String texto, Pageable pageable) {
        Usuario eu = usuarioAutenticadoService.get();

       return amizadeRepository
                .filterAmizadesByNomeOrEmail(StatusAmizade.ACEITO, eu.getId(), texto, pageable)
               .map(PesquisarUsuarioMapper::toResponse);


    }
}