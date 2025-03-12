package br.com.pedidosEcom.dto;

import java.math.BigDecimal;

public record FaturamentoMensalDTO(BigDecimal faturamentoMensal) {

    public FaturamentoMensalDTO(Number faturamentoMensal) {
        this(BigDecimal.valueOf(faturamentoMensal.doubleValue()));
    }
}
