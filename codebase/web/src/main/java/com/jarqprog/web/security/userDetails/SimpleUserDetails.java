package com.jarqprog.web.security.userDetails;


import com.jarqprog.personapi.domains.user.UserEntity;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ToString(callSuper = true)
public class SimpleUserDetails extends UserEntity implements UserDetails {

//    @NonNull
//    private final RoleUserRepository roleUserRepository;
//
//    @Autowired
//    SimpleUserDetails(final UserEntity user, @NonNull RoleUserRepository roleUserRepository) {
//        super(user);
//        this.roleUserRepository = roleUserRepository;
//    }
        SimpleUserDetails(final UserEntity user) {
        super(user);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roleUserRepository.findAllByUserEntityLogin(this.getLogin())
//                .stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
//                .collect(Collectors.toList());
        return List.of("USER", "ADMIN")
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
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
