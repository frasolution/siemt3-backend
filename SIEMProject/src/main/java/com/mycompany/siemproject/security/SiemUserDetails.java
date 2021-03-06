package com.mycompany.siemproject.security;

import com.mycompany.siemproject.model.User;
import static com.mycompany.siemproject.security.Role.ROLE_ADMIN;
import static com.mycompany.siemproject.security.Role.ROLE_USER;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SiemUserDetails implements UserDetails {

    private final User user;
    private final List<GrantedAuthority> authorities = new ArrayList();
    
    public SiemUserDetails(final User user) {
        this.user = user;
        setAuthorities();
    }
    
    private void setAuthorities() {
        if (user.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN.name()));
        }
        authorities.add(new SimpleGrantedAuthority(ROLE_USER.name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    public String getName() {
        return user.getFirstName() + " " + user.getLastName();
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
