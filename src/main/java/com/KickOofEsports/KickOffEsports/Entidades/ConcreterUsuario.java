package com.KickOofEsports.KickOffEsports.Entidades;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ConcreterUsuario extends User {
    public ConcreterUsuario(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
