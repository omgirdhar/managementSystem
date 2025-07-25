package com.example.demo.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.constants.EnumConstants.Role;
import com.example.demo.constants.EnumConstants.Status;
import com.example.demo.model.User;

public class CustomUserDetails implements UserDetails{

    private String email;
    private String password;
    private boolean enabled;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.getStatus() == Status.ACTIVE;
        this.authorities = Arrays.stream(new Role[] {user.getRole()})
               .map(role -> new SimpleGrantedAuthority(role.name()))
               .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities; 
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
        return this.enabled;
    }

}
