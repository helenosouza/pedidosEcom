package br.com.pedidosEcom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EqualsAndHashCode
public class Auditoria {

    private LocalDateTime dataAlteracao;
    @Column(updatable = false)
    private LocalDateTime dataCadastro;

    @PrePersist
    protected void onCreate() {
        prePersistInternal();
        LocalDateTime dataHora = LocalDateTime.now();

        dataCadastro = dataHora;
        dataAlteracao = dataHora;

    }

    @PreUpdate
    protected void onUpdate() {
        dataAlteracao = LocalDateTime.now();
    }

    protected void prePersistInternal() {
    }
}

