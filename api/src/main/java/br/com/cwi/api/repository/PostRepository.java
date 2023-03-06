package br.com.cwi.api.repository;

import br.com.cwi.api.domain.Post;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {


    List<Post> findAllByAutor(Usuario autor);
}
