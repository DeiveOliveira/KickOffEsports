package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisualizarService {

    @Autowired
    ProdutoRepository repository;

    public Optional<Produto> procurarPorId(String id){
        return Optional.of(repository.getReferenceById(id));
    }
}
