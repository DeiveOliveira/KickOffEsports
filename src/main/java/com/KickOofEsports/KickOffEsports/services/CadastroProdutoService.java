package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public Produto cadastrarProduto(Produto produto){
        Produto  pro = new Produto(produto.getNome(), produto.getAvaliacao(), produto.getDescricao(), produto.getPreco(), produto.getQuantidade());
        return produtoRepository.save(pro);
    }

}
