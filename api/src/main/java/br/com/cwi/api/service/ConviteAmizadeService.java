package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.MensagemResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.enums.StatusAmizade;
import br.com.cwi.api.repository.AmizadeRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.service.BuscarUsuarioService;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.validator.VerificarExisteAmizadeJaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConviteAmizadeService {

    @Autowired
    private VerificarExisteAmizadeJaValidator verificarExisteAmizadeJaValidator;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public MensagemResponse aceitar(Long idAmigo) {
        Usuario eu = usuarioAutenticadoService.get();
        Usuario usuarioAceito = buscarUsuarioService.porId(idAmigo);

        verificarExisteAmizadeJaValidator.verificarConvite(eu.getId(), usuarioAceito.getId());

        Amizade amizade = amizadeRepository
                .filterPedidoAmizade(usuarioAceito.getId(), eu.getId(), StatusAmizade.SOLICITADO).get(0);

        amizade.setStatus(StatusAmizade.ACEITO);

        amizadeRepository.save(amizade);

        return MensagemResponse.builder()
                .response("Você aceitou a solicitação de amizade de " + usuarioAceito.getApelido())
                .build();
    }

    public MensagemResponse negar(Long idAmigo) {
        Usuario eu = usuarioAutenticadoService.get();
        Usuario usuarioNegado = buscarUsuarioService.porId(idAmigo);

        verificarExisteAmizadeJaValidator.verificarConvite(eu.getId(), usuarioNegado.getId());

        Amizade amizade = amizadeRepository
                .filterPedidoAmizade(usuarioNegado.getId(), eu.getId(), StatusAmizade.SOLICITADO).get(0);

        amizade.setStatus(StatusAmizade.NEGADO);

        amizadeRepository.save(amizade);

        return MensagemResponse.builder()
                .response("Você negou a solicitação de amizade de " + usuarioNegado.getApelido())
                .build();
    }
}
