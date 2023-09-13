package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Imagens;
import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.repositories.ListaDeImagensRepository;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class CadastroProdutoService {

    @Autowired
    ListaDeImagensRepository listaDeImagensRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    public Produto cadastrarProduto(Produto produto){
        Produto pro = new Produto(produto.getNome(), produto.getAvaliacao(),
                produto.getDescricao(), produto.getPreco(), produto.getQuantidade());
        pro = produtoRepository.save(pro);
        for (Imagens imagem : produto.getImg()) {
            imagem.setProduto(pro);
            Imagens imagemSalva = listaDeImagensRepository.save(imagem);
            pro.getImg().add(imagemSalva);
        }
        return pro;
    }







}
