package br.com.pedidosEcom.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TopUsuarioDTO(UUID usuarioId, String nome, BigDecimal totalComprado) {}
