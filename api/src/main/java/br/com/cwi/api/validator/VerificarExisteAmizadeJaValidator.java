package br.com.cwi.api.validator;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class VerificarExisteAmizadeJaValidator {

    @Autowired
    private AmizadeRepository amizadeRepository;

    public void verificar(Long idUsuario, Long idAmigo) {

        List<Amizade> amizade = amizadeRepository.filterStatusByDestinatarioAmizade(idAmigo, idUsuario);

        if (amizade.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não pode enviar convite novamente");
        }
    }

    public void verificarConvite(Long idUsuario, Long idAmigo) {

        List<Amizade> amizade = amizadeRepository.filterStatusByDestinatarioAmizade(idAmigo, idUsuario);

        if (amizade.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe convite pendente");
        }
    }
}
