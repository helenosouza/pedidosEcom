package br.com.pedidosEcom.repository;

import br.com.pedidosEcom.entity.Pedido;
import br.com.pedidosEcom.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    List<Pedido> findByUsuario(Usuario usuario);
}
