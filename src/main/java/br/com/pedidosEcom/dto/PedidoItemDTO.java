package br.com.pedidosEcom.dto;

import java.util.UUID;

public record PedidoItemDTO(UUID produtoId, Integer quantidade) {}
