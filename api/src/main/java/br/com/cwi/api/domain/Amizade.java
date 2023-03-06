package br.com.cwi.api.domain;

import br.com.cwi.api.domain.enums.StatusAmizade;
import br.com.cwi.api.security.domain.Usuario;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id") @ToString(of = "id")
@Entity
@Builder
public class Amizade {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "destinatario_id")
    private Usuario destinatario;

    @ManyToOne()
    @JoinColumn(name = "remetente_id")
    private Usuario remetente;

    @Enumerated(EnumType.STRING)
    private StatusAmizade status;
}
