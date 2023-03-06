package br.com.cwi.api.service;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.enums.StatusAmizade;
import br.com.cwi.api.repository.AmizadeRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAmigosService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public List<Usuario> get() {
        Usuario autor = usuarioAutenticadoService.get();
        List<Amizade> amizades = amizadeRepository
                .filterAmizades(autor.getId(), StatusAmizade.ACEITO);

        List<Usuario> amigos = new ArrayList<>();

        amizades.stream().map(amizade -> amizade.getRemetente())
                .filter(remetente -> remetente!=autor)
                .forEach(remetente -> amigos.add(remetente));

        amizades.stream().map(amizade -> amizade.getDestinatario())
                .filter(destinatario -> destinatario!=autor)
                .forEach(destinatario -> amigos.add(destinatario));

        return amigos;
    }
}
