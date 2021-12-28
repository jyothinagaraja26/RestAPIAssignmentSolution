package com.greatlearning.employee.security;

import com.greatlearning.employee.entities.Roles;
import com.greatlearning.employee.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private User user;
    public MyUserDetails(User user){
        this.user=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Roles> roles=user.getListOfRoles();
         List<SimpleGrantedAuthority> authority=new ArrayList<>();
        for(Roles role:roles){
            authority.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authority;
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
