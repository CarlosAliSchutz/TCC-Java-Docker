package br.com.cwi.api.repository;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.enums.StatusAmizade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AmizadeRepository extends JpaRepository<Amizade, Long> {

    @Query("select a from Amizade a where" +
            " ((a.remetente.id=?1 " +
            "and a.destinatario.id=?2)" +
            " or (a.remetente.id=?2 " +
            "and a.destinatario.id=?1))")
    List<Amizade> filterStatusByDestinatarioAmizade(Long remetente, Long destinatario);

    @Query("select a from Amizade a where " +
            "(a.remetente.id=?1 and a.destinatario.id=?2) " +
            "and a.status=?3 ")
    List<Amizade> filterPedidoAmizade(Long remetente, Long destinatario, StatusAmizade status);


    @Query("select a from Amizade a where " +
            " (a.remetente.id=?1 or a.destinatario.id=?1)" +
            "and a.status=?2 ")
    List<Amizade> filterAmizades(Long userId, StatusAmizade status);

    @Query("select a from Amizade a where " +
            "(a.destinatario.id=?2 or a.remetente.id=?2) and a.status=?1 " +
            "and (UPPER(a.destinatario.nome) like UPPER(concat('%', ?3, '%')) or UPPER(a.destinatario.email) like UPPER(concat('%', ?3, '%')) " +
            "or UPPER(a.remetente.nome) like UPPER(concat('%', ?3, '%')) or UPPER(a.remetente.email) like UPPER(concat('%', ?3, '%')))")
    Page<Amizade> filterAmizadesByNomeOrEmail(StatusAmizade status, Long id, String texto, Pageable pageable);


    @Query("select a from Amizade a where " +
            "(a.destinatario.id=?2) and a.status=?1")
    List<Amizade> filterAmizadesPendentes(StatusAmizade solicitado, Long id);
}
