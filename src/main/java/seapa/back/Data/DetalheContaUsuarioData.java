package seapa.back.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import seapa.back.Entitys.UserManegerEntitys.UserEntitys.ContaUsuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetalheContaUsuarioData implements UserDetails {

    private final Optional<ContaUsuario> usuario;

    public DetalheContaUsuarioData(Optional<ContaUsuario> usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return usuario.orElse(new ContaUsuario()).getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.orElse(new ContaUsuario()).getNomeUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
