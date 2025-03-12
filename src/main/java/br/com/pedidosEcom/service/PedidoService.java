package br.com.pedidosEcom.service;

import br.com.pedidosEcom.dto.PedidoCreateDTO;
import br.com.pedidosEcom.dto.PedidoDTO;
import br.com.pedidosEcom.entity.Pedido;
import br.com.pedidosEcom.entity.PedidoItem;
import br.com.pedidosEcom.entity.Produto;
import br.com.pedidosEcom.entity.Usuario;
import br.com.pedidosEcom.enums.StatusPedido;
import br.com.pedidosEcom.repository.PedidoRepository;
import br.com.pedidosEcom.repository.ProdutoRepository;
import br.com.pedidosEcom.utils.UsuarioUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public PedidoDTO criarPedido(PedidoCreateDTO pedidoCreateDTO) {
        Usuario usuario = UsuarioUtil.getUsuarioLogado();
        if (usuario == null) {
            throw new EntityNotFoundException("Usuário não autenticado");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setStatus(StatusPedido.PENDENTE);

        List<PedidoItem> itens = pedidoCreateDTO.itens().stream().map(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.produtoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado: " + itemDTO.produtoId()));

            PedidoItem item = new PedidoItem();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemDTO.quantidade());
            item.setPrecoProduto(produto.getPreco());
            return item;
        }).collect(Collectors.toList());

        pedido.setItens(itens);
        BigDecimal total = itens.stream()
                .map(item -> item.getPrecoProduto().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        pedido.setValorTotal(total);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return new PedidoDTO(pedidoSalvo, "");
    }

    @Transactional
    public PedidoDTO pagarPedido(UUID pedidoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado: " + pedidoId));

        if (pedido.getStatus() != StatusPedido.PENDENTE) {
            return new PedidoDTO(pedido, "");
        }

        BigDecimal novoTotal = BigDecimal.ZERO;
        String descricaoCancelamento = "";

        for (PedidoItem item : pedido.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado: " + item.getProduto().getId()));

            if (produto.getQuantidadeEmEstoque() < item.getQuantidade()) {
                descricaoCancelamento = "Produto " + produto.getNome() + " sem estoque suficiente. Pedido cancelado.";
                pedido.setStatus(StatusPedido.CANCELADO);
                pedidoRepository.save(pedido);
                return new PedidoDTO(pedido, descricaoCancelamento);
            }
            item.setPrecoProduto(produto.getPreco());
            novoTotal = novoTotal.add(produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
        }

        for (PedidoItem item : pedido.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado: " + item.getProduto().getId()));
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - item.getQuantidade());
            produtoRepository.save(produto);
        }

        pedido.setValorTotal(novoTotal);
        pedido.setStatus(StatusPedido.PAGO);
        pedido.setDataPagamento(LocalDateTime.now());

        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        return new PedidoDTO(pedidoAtualizado, "");
    }

    @Transactional(readOnly = true)
    public List<PedidoDTO> listarPedidosDoUsuario() {
        Usuario usuario = UsuarioUtil.getUsuarioLogado();
        if (usuario == null) {
            throw new EntityNotFoundException("Usuário não autenticado");
        }
        return pedidoRepository.findByUsuario(usuario)
                .stream()
                .map(pedido -> new PedidoDTO(pedido, ""))
                .collect(Collectors.toList());
    }
}
