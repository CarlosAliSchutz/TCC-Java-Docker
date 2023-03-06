package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.ListarAmizadesResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.enums.StatusAmizade;
import br.com.cwi.api.mapper.PesquisarUsuarioMapper;
import br.com.cwi.api.repository.AmizadeRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarAmizadesService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private AmizadeRepository amizadeRepository;


    public List<ListarAmizadesResponse> listarPendentes() {

        Usuario eu = usuarioAutenticadoService.get();

        return amizadeRepository.filterAmizadesPendentes(StatusAmizade.SOLICITADO, eu.getId()).stream()
                .map(PesquisarUsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<Usuario> listarAmigos() {
        Usuario eu = usuarioAutenticadoService.get();

        List<Amizade> amizades = amizadeRepository.filterAmizadesPendentes(StatusAmizade.ACEITO, eu.getId());

        return amizades.stream()
                .map(amizade -> amizade.getRemetente().equals(eu) ? amizade.getDestinatario() : amizade.getRemetente())
                .collect(Collectors.toList());
    }
}

