package com.jarqprog.artGallery.springSecurity.userDetails;

import com.jarqprog.artGallery.domain.entity.User;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@ToString(callSuper = true)
public class SimpleUserDetails extends User implements UserDetails {


    SimpleUserDetails(final User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return getLogin();
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
        return getEnabled();
    }
}
