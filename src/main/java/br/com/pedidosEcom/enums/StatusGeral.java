package br.com.pedidosEcom.enums;

public enum StatusGeral {
    ATIVO("ATIVO", "ATIVO"),
    INATIVO("INATIVO", "INATIVO"),
    EXCLUIDO("EXCLUIDO", "EXCLUÍDO"),
    CANCELADO("CANCELADO", "CANCELADO");


    private String codigo;
    private String valor;


    StatusGeral(String codigo, String valor) {
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
