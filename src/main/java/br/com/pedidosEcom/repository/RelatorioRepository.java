package br.com.pedidosEcom.repository;

import br.com.pedidosEcom.dto.FaturamentoMensalDTO;
import br.com.pedidosEcom.dto.TicketMedioDTO;
import br.com.pedidosEcom.dto.TopUsuarioDTO;
import br.com.pedidosEcom.entity.Pedido;
import br.com.pedidosEcom.enums.StatusPedido;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RelatorioRepository extends Repository<Pedido, UUID> {

    @Query("select new br.com.pedidosEcom.dto.TopUsuarioDTO(u.id, u.nome, sum(p.valorTotal)) " +
            "from Pedido p join p.usuario u " +
            "where p.status = :status " +
            "group by u.id, u.nome " +
            "order by sum(p.valorTotal) desc")
    List<TopUsuarioDTO> findTop5Usuarios(@Param("status") StatusPedido status, Pageable pageable);

    @Query("select new br.com.pedidosEcom.dto.TicketMedioDTO(u.id, u.nome, avg(p.valorTotal)) " +
            "from Pedido p join p.usuario u " +
            "where p.status = :status " +
            "group by u.id, u.nome")
    List<TicketMedioDTO> findTicketMedioPorUsuario(@Param("status") StatusPedido status);

    @Query("select new br.com.pedidosEcom.dto.FaturamentoMensalDTO(coalesce(sum(p.valorTotal), 0)) " +
            "from Pedido p " +
            "where p.status = :status " +
            "and function('MONTH', p.dataPagamento) = function('MONTH', current_date) " +
            "and function('YEAR', p.dataPagamento) = function('YEAR', current_date)")
    FaturamentoMensalDTO findFaturamentoMensal(@Param("status") StatusPedido status);
}
