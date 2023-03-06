package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.MensagemResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.enums.StatusAmizade;
import br.com.cwi.api.repository.AmizadeRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.BuscarUsuarioService;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.validator.VerificarExisteAmizadeJaValidator;
import br.com.cwi.api.validator.VerificarSolicitacaoAmizadeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoAmizadeService {

    @Autowired
    private VerificarExisteAmizadeJaValidator verificarExisteAmizadeJaValidator;

    @Autowired
    private VerificarSolicitacaoAmizadeValidator verificarSolicitacaoAmizadeValidator;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    public MensagemResponse solicitar(Long idAmigo) {

        verificarSolicitacaoAmizadeValidator.verificarSolicitacaoAmizade(idAmigo);
        Usuario amigo = buscarUsuarioService.porId(idAmigo);
        Usuario eu = usuarioAutenticadoService.get();

//        verificarExisteAmizadeJaValidator.verificar(eu.getId(), amigo.getId());

        Amizade amizade = Amizade.builder()
                .status(StatusAmizade.SOLICITADO)
                .remetente(eu)
                .destinatario(amigo)
                .build();

        amizadeRepository.save(amizade);

        return MensagemResponse.builder()
                .response("Você enviou convite de amizade para "+ amigo.getApelido())
                .build();

    }
}
