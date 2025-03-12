package br.com.pedidosEcom.dto;

import br.com.pedidosEcom.entity.Pedido;
import br.com.pedidosEcom.enums.StatusPedido;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record PedidoDTO(
        UUID id,
        UUID usuarioId,
        List<PedidoItemDTO> itens,
        BigDecimal valorTotal,
        StatusPedido status,
        String descricao
) {
    public PedidoDTO(Pedido pedido, String descricao) {
        this(
                pedido.getId(),
                pedido.getUsuario().getId(),
                pedido.getItens().stream()
                        .map(item -> new PedidoItemDTO(item.getProduto().getId(), item.getQuantidade()))
                        .collect(Collectors.toList()),
                pedido.getValorTotal(),
                pedido.getStatus(),
                descricao
        );
    }
}
