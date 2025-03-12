package br.com.pedidosEcom.service;

import br.com.pedidosEcom.dto.UsuarioDTO;
import br.com.pedidosEcom.entity.Role;
import br.com.pedidosEcom.entity.Usuario;
import br.com.pedidosEcom.entity.UsuarioRole;
import br.com.pedidosEcom.repository.RoleRepository;
import br.com.pedidosEcom.repository.UsuarioRepository;
import br.com.pedidosEcom.repository.UsuarioRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public UsuarioDTO criarUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());

        if (dto.roles() != null && !dto.roles().isEmpty()) {
            List<UsuarioRole> usuarioRoles = dto.roles().stream().map(roleName -> {
                Role role = roleRepository.findByNome(roleName)
                        .orElseThrow(() -> new EntityNotFoundException("Role não encontrada: " + roleName));
                UsuarioRole usuarioRole = new UsuarioRole();
                usuarioRole.setUsuario(usuario);
                usuarioRole.setRole(role);
                return usuarioRole;
            }).toList();
            usuario.setUsuarioRoles(usuarioRoles);
        }

        Usuario saved = usuarioRepository.save(usuario);
        return new UsuarioDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioDTO buscarUsuario(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return new UsuarioDTO(usuario);
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(UUID id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());

        usuario.getUsuarioRoles().clear();

        if (dto.roles() != null && !dto.roles().isEmpty()) {
            List<UsuarioRole> usuarioRoles = dto.roles().stream().map(roleName -> {
                Role role = roleRepository.findByNome(roleName)
                        .orElseThrow(() -> new EntityNotFoundException("Role não encontrada: " + roleName));
                UsuarioRole usuarioRole = new UsuarioRole();
                usuarioRole.setUsuario(usuario);
                usuarioRole.setRole(role);
                return usuarioRole;
            }).toList();
            usuario.setUsuarioRoles(usuarioRoles);
        }

        Usuario updated = usuarioRepository.save(usuario);
        return new UsuarioDTO(updated);
    }

    @Transactional
    public void deletarUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }
}
