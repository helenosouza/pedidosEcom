package br.com.pedidosEcom.enums;

public enum StatusPedido {
    PENDENTE("PENDENTE", "PENDENTE"),
    PAGO("PAGO", "PAGO"),
    CANCELADO("CANCELADO", "CANCELADO");

    private String codigo;
    private String valor;


    StatusPedido(String codigo, String valor) {
        this.codigo = codigo;
        this.valor = valor;
    }


    public String getCodigo() {
        return this.codigo;
    }


    public String getValor() {
        return this.valor;
    }
}
