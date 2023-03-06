package br.com.cwi.api.security.domain;

import br.com.cwi.api.domain.Curtida;
import br.com.cwi.api.domain.Post;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id") @ToString(of = "id")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String apelido;
    private LocalDate dataNascimento;
    private String senha;
    private String imgPerfil;
    private boolean ativo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permissao> permissoes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Curtida> curtidas;

    @OneToMany(mappedBy = "autor")
    private List<Post> posts;

    public void adicionarPermissao(Permissao permissao) {
        this.permissoes.add(permissao);
        permissao.setUsuario(this);
    }

}
