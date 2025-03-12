package br.com.pedidosEcom.repository;

import br.com.pedidosEcom.entity.UsuarioRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRoleRepository extends JpaRepository<UsuarioRole, UUID> {
}
