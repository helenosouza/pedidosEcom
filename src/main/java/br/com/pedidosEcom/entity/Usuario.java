package br.com.pedidosEcom.entity;

import br.com.pedidosEcom.enums.StatusGeral;
import br.com.pedidosEcom.utils.PasswordAttributeConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Usuario extends Auditoria implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Convert(converter = PasswordAttributeConverter.class)
    @Column(nullable = false)
    private String senha;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioRole> usuarioRoles;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusGeral status;

    @Override
    protected void prePersistInternal() {
        status = StatusGeral.ATIVO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuarioRoles.stream()
                .map(usuarioRole -> (GrantedAuthority) () -> usuarioRole.getRole().getNome())
                .toList();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Customize aqui a lógica real, se necessário
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Customize aqui a lógica real, se necessário
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Customize aqui a lógica real, se necessário
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Pode retornar, por exemplo, status == StatusGeral.ATIVO
        return true;
    }
}
