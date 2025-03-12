package br.com.pedidosEcom.dto;

import br.com.pedidosEcom.entity.Usuario;
import java.util.List;
import java.util.UUID;

public record UsuarioDTO(
        UUID id,
        String nome,
        String senha,
        String email,
        List<String> roles
) {

    public UsuarioDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getSenha(),
                usuario.getEmail(),
                usuario.getUsuarioRoles().stream()
                        .map(usuarioRole -> usuarioRole.getRole().getNome())
                        .toList()
        );
    }

    public UsuarioResponseDTO min() {
        return new UsuarioResponseDTO(this.id, this.nome, this.email);
    }

    public record UsuarioResponseDTO(
            UUID id,
            String nome,
            String email
    ) {
    }
}
