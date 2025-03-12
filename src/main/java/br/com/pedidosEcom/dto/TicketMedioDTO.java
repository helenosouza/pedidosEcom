package br.com.pedidosEcom.dto;

import java.util.UUID;

public record TicketMedioDTO(UUID usuarioId, String nome, Double ticketMedio) {}
