package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import com.KickOofEsports.KickOffEsports.services.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditarProdutoService {

    @Autowired
    ProdutoRepository repository;

    public Optional<Produto> procurarPorId(String id){
        return Optional.of(repository.getReferenceById(id));
    }

    public Produto atualizar(String id, Produto produtoAtualizado){
        try{
            Produto produtoDesatualizado = repository.getReferenceById(id);
            atualizarData(produtoDesatualizado, produtoAtualizado);
            return repository.save(produtoDesatualizado);
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontradoException(id);
        }
    }

    public void atualizarData(Produto produtoDesatualizado, Produto produtoAtualizado){
        produtoDesatualizado.setNome(produtoAtualizado.getNome());
        produtoDesatualizado.setAvaliacao(produtoAtualizado.getAvaliacao());
        produtoDesatualizado.setDescricao(produtoAtualizado.getDescricao());
        produtoDesatualizado.setPreco(produtoAtualizado.getPreco());
        produtoDesatualizado.setQuantidade(produtoAtualizado.getQuantidade());
    }
}
