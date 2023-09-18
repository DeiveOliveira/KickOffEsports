package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Imagens;
import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.repositories.ListaDeImagensRepository;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Produto cadastrarProduto(Produto produto, @RequestParam("files") List<MultipartFile> imagens) {
        Produto pro = new Produto(produto.getNome(), produto.getAvaliacao(),
                produto.getDescricao(), produto.getPreco(), produto.getQuantidade());
        pro = produtoRepository.save(pro);

        for (MultipartFile imagem : imagens) {
            try {
                if (imagem != null && !imagem.isEmpty()) {
                    byte[] bytes = imagem.getBytes();
                    String fileName = System.currentTimeMillis() + "_" + imagem.getOriginalFilename();

                    Path caminho = Paths.get("../static/img/imagensProdutos/" + fileName);
                    Files.write(caminho, bytes);

                    Imagens img = new Imagens();
                    img.setUrl(fileName);
                    img.setProduto(pro); // Associe a imagem ao produto
                    img = listaDeImagensRepository.save(img);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return pro;
    }
}
