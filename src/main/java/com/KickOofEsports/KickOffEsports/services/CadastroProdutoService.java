package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Imagens;
import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.repositories.ListaDeImagensRepository;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CadastroProdutoService {

    @Autowired
    ListaDeImagensRepository listaDeImagensRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    public Produto cadastrarProduto(Produto produto) {
        Produto pro = new Produto(produto.getNome(), produto.getAvaliacao(),
                produto.getDescricao(), produto.getPreco(), produto.getQuantidade());
        pro = produtoRepository.save(pro);
        return pro;
    }
}
