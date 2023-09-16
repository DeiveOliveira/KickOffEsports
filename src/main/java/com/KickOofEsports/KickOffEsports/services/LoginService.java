package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    Usuario usuario;
    @Autowired
    UsuariosRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
        usuario = repository.findByEmail(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Conta errada!");
        }
        return usuario;
    }
}
