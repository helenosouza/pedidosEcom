package br.com.pedidosEcom.dto;

import java.util.List;

public record PedidoCreateDTO(
        List<PedidoItemDTO> itens
) {}
