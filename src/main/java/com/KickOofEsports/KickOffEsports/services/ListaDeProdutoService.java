package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import com.KickOofEsports.KickOffEsports.services.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaDeProdutoService {

    @Autowired
    ProdutoRepository repository;

    public Produto atualizarStatus(String id){
        try{
            Produto produto = repository.getReferenceById(id);
            ativarDesativar(produto);
            return repository.save(produto);
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontradoException(id);
        }
    }

    private void ativarDesativar(Produto produto){
        if (produto.getStatus() == true){
            produto.setStatus(false);
        }else{
            produto.setStatus(true);
        }
    }

    public List<Produto> pesquisaPorNome(String nome){
        List<Produto> list = repository.findByNome(nome);
        return list;
    }
}
