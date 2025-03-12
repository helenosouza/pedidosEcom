package br.com.pedidosEcom.service;

import br.com.pedidosEcom.dto.FaturamentoMensalDTO;
import br.com.pedidosEcom.dto.TicketMedioDTO;
import br.com.pedidosEcom.dto.TopUsuarioDTO;
import br.com.pedidosEcom.enums.StatusPedido;
import br.com.pedidosEcom.repository.RelatorioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;

    public RelatorioService(RelatorioRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    public List<TopUsuarioDTO> getTop5Usuarios() {
        return relatorioRepository.findTop5Usuarios(StatusPedido.PAGO, PageRequest.of(0, 5));
    }

    public List<TicketMedioDTO> getTicketMedioPorUsuario() {
        return relatorioRepository.findTicketMedioPorUsuario(StatusPedido.PAGO);
    }

    public FaturamentoMensalDTO getFaturamentoMensal() {
        return relatorioRepository.findFaturamentoMensal(StatusPedido.PAGO);
    }
}
