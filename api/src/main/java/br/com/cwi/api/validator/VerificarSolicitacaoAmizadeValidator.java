package br.com.cwi.api.validator;

import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class VerificarSolicitacaoAmizadeValidator {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public void verificarSolicitacaoAmizade(Long id) {

        Usuario usuario = usuarioAutenticadoService.get();

        if (usuario.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não pode mandar solicitação de amizade para você mesmo");
        }
    }

}
