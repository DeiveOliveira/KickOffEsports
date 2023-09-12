package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import com.KickOofEsports.KickOffEsports.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastrarService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    UsuariosRepository repository;

    public Usuario cadastrar(Usuario user){
        String criptografando = this.passwordEncoder.encode(user.getSenha());
        user.setSenha(criptografando);
        Usuario usuario = new Usuario(user.getNome(), user.getEmail(), user.getCpf(), user.getSenha(), user.getRole().toString());
        return repository.save(usuario);
    }


}
