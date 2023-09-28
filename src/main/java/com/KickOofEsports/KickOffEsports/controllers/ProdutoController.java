package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> todosProdutos(){

        return produtoRepository.findAll();
    }
}
