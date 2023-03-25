package com.euce.dessert.security.service;

import com.euce.dessert.model.account.Privilege;
import com.euce.dessert.model.account.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDetailsImpl implements UserDetails {
    private final User user;

    @Autowired
    public UserDetailsImpl(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        List<Privilege> rolePrivileges = user.getRoles().stream()
                .map(role -> role.getPrivileges())
                .flatMap(Set::stream)
                .collect(Collectors.toList());

        List<Privilege> groupPrivileges = user.getGroups().stream()
                .map(group -> group.getPrivileges())
                .flatMap(Set::stream)
                .collect(Collectors.toList());

        List<Privilege> privileges = Stream.of(rolePrivileges, groupPrivileges)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        for (Privilege privilege: privileges) {
            grantedAuthorities.add(new SimpleGrantedAuthority(privilege.getName().toString()));
        }

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
