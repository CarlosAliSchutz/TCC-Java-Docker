package br.com.cwi.api.controller;

import br.com.cwi.api.controller.response.ListarAmizadesResponse;
import br.com.cwi.api.controller.response.MensagemResponse;
import br.com.cwi.api.service.ConviteAmizadeService;
import br.com.cwi.api.service.ListarAmizadesService;
import br.com.cwi.api.service.PesquisarUsuariosService;
import br.com.cwi.api.service.SolicitacaoAmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amizade")
public class AmizadeController {

    @Autowired
    private SolicitacaoAmizadeService solicitacaoAmizadeService;

    @Autowired
    private PesquisarUsuariosService pesquisarUsuariosService;

    @Autowired
    private ListarAmizadesService listarAmizadesService;

    @Autowired
    private ConviteAmizadeService conviteAmizadeService;

    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<ListarAmizadesResponse> listar() {
        return listarAmizadesService.listarPendentes();
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/search")
    public Page<ListarAmizadesResponse> pesquisar(@RequestParam String texto, Pageable pageable){
        return pesquisarUsuariosService.pesquisar(texto, pageable);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/{idAmigo}")
    public MensagemResponse solicitar(@PathVariable Long idAmigo){
        return solicitacaoAmizadeService.solicitar(idAmigo);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{idAmigo}/aceitarConvite")
    public MensagemResponse aceitar(@PathVariable Long idAmigo) {
        return conviteAmizadeService.aceitar(idAmigo);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{idAmigo}/negarConvite")
    public MensagemResponse negar(@PathVariable Long idAmigo) {
        return conviteAmizadeService.negar(idAmigo);
    }
}
