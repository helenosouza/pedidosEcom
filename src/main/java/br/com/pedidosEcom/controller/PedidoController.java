package br.com.pedidosEcom.controller;

import br.com.pedidosEcom.dto.PedidoCreateDTO;
import br.com.pedidosEcom.dto.PedidoDTO;
import br.com.pedidosEcom.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoCreateDTO pedidoCreateDTO) {
        return ResponseEntity.ok(pedidoService.criarPedido(pedidoCreateDTO));
    }

    @PostMapping("/{id}/pagar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PedidoDTO> pagarPedido(@PathVariable UUID id) {
        return ResponseEntity.ok(pedidoService.pagarPedido(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<PedidoDTO>> listarPedidosDoUsuario() {
        return ResponseEntity.ok(pedidoService.listarPedidosDoUsuario());
    }
}
