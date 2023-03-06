package br.com.cwi.api.domain;

import br.com.cwi.api.domain.enums.Disponivel;
import br.com.cwi.api.security.domain.Usuario;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Builder @AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id") @ToString(of = "id")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private String conteudo;

    private LocalDate criacao;

    @Enumerated(EnumType.STRING)
    private Disponivel disponivel;

    @OneToMany(mappedBy = "post")
    private List<Curtida> curtidas = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Comentario> comentarios;

    private Integer curtidasPost;
}
