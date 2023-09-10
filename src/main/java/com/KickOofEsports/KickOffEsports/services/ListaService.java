package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.UsuariosRepository;
import com.KickOofEsports.KickOffEsports.services.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaService {

    @Autowired
    UsuariosRepository repository;

    public Usuario atualizarStatus(String id){
        try{
            Usuario usuario = repository.getReferenceById(id);
            ativarDesativar(usuario);
            return repository.save(usuario);
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontradoException(id);
        }
    }

    private void ativarDesativar(Usuario user){
        if (user.getStatus() == true){
            user.setStatus(false);
        }else{
            user.setStatus(true);
        }
    }

    public List<Usuario> pesquisaPorNome(String nome){
        List<Usuario> list = repository.findByNome(nome);
        return list;
    }
}
