package br.com.pedidosEcom.controller;

import br.com.pedidosEcom.dto.FaturamentoMensalDTO;
import br.com.pedidosEcom.dto.TicketMedioDTO;
import br.com.pedidosEcom.dto.TopUsuarioDTO;
import br.com.pedidosEcom.service.RelatorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/top-usuarios")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<TopUsuarioDTO>> getTopUsuarios() {
        List<TopUsuarioDTO> topUsuarios = relatorioService.getTop5Usuarios();
        return ResponseEntity.ok(topUsuarios);
    }

    @GetMapping("/ticket-medio")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<TicketMedioDTO>> getTicketMedio() {
        List<TicketMedioDTO> ticketMedio = relatorioService.getTicketMedioPorUsuario();
        return ResponseEntity.ok(ticketMedio);
    }

    @GetMapping("/faturamento-mensal")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<FaturamentoMensalDTO> getFaturamentoMensal() {
        FaturamentoMensalDTO faturamentoMensal = relatorioService.getFaturamentoMensal();
        return ResponseEntity.ok(faturamentoMensal);
    }
}
