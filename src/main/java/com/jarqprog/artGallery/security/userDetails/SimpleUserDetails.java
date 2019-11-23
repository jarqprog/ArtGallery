package com.jarqprog.artGallery.security.userDetails;

import com.jarqprog.artGallery.api.domains.personal.user.UserEntity;
import com.jarqprog.artGallery.api.domains.personal.roleUser.RoleUserRepository;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@ToString(callSuper = true)
public class SimpleUserDetails extends UserEntity implements UserDetails {

    @NonNull
    private final RoleUserRepository roleUserRepository;

    @Autowired
    SimpleUserDetails(final UserEntity user, @NonNull RoleUserRepository roleUserRepository) {
        super(user);
        this.roleUserRepository = roleUserRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleUserRepository.findAllByUserEntityLogin(this.getLogin())
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
