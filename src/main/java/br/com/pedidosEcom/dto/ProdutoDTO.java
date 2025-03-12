package br.com.pedidosEcom.dto;

import br.com.pedidosEcom.entity.Produto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutoDTO(
        UUID id,
        String nome,
        String descricao,
        BigDecimal preco,
        String categoria,
        Integer quantidadeEmEstoque,
        LocalDateTime dataCadastro,
        LocalDateTime dataAlteracao
) {
    public ProdutoDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoria(),
                produto.getQuantidadeEmEstoque(),
                produto.getDataCadastro(),
                produto.getDataAlteracao()
        );
    }

    public Produto toEntity() {
        return new Produto(
            this.id,
            this.nome,
            this.descricao,
            this.preco,
            this.categoria,
            this.quantidadeEmEstoque
        );
    }
}
