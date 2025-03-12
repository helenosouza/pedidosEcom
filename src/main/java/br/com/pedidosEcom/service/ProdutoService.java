package br.com.pedidosEcom.service;

import br.com.pedidosEcom.dto.ProdutoDTO;
import br.com.pedidosEcom.entity.Produto;
import br.com.pedidosEcom.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public ProdutoDTO criarProduto(ProdutoDTO dto) {
        Produto salvo = produtoRepository.save(dto.toEntity());
        return new ProdutoDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> listarProdutos() {
        return produtoRepository.findAll().stream()
                .map(ProdutoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProdutoDTO buscarProduto(UUID id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        return new ProdutoDTO(produto);
    }

    @Transactional
    public ProdutoDTO atualizarProduto(UUID id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        Produto atualizado = produtoRepository.save(dto.toEntity());
        return new ProdutoDTO(atualizado);
    }

    @Transactional
    public void deletarProduto(UUID id) {
        produtoRepository.deleteById(id);
    }
}