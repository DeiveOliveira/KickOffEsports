package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.UsuariosRepository;
import com.KickOofEsports.KickOffEsports.services.exceptions.SenhaDiferenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    UsuariosRepository usuariosRepository;

    public Usuario login(String email, String senha){
        Usuario usuario = usuariosRepository.findByEmail(email);
        if(usuario != null && validarSenha(usuario, senha)){
            return usuario;
        }
        throw new SenhaDiferenteException();
    }

    public boolean validarSenha(Usuario usuario, String senha){
        return passwordEncoder.matches(senha, usuario.getSenha());
    }

}
