package br.com.pedidosEcom.repository;

import br.com.pedidosEcom.entity.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    @EntityGraph(attributePaths = {"usuarioRoles", "usuarioRoles.role"})
    Optional<Usuario> findByEmail(String email);
}
