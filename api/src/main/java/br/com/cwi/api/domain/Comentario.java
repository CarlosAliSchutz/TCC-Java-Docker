package br.com.cwi.api.domain;

import br.com.cwi.api.security.domain.Usuario;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id") @ToString(of = "id")
@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String comentario;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
