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

public class UserDetailsImpl implements UserDetails {
    private final User user;

    @Autowired
    public UserDetailsImpl(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        String userRoleName = user.getRole().getName().toString();
        String userGroupName = user.getGroup().getName().toString();
        System.out.println(userRoleName);
        System.out.println(userGroupName);
        for (Privilege privilege : user.getRole().getPrivileges()) {
            if (privilege.getGroups().stream().anyMatch(group -> group.getName().equals(userGroupName))){
                grantedAuthorities.add(new SimpleGrantedAuthority(privilege.getName().toString()));
            }
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
