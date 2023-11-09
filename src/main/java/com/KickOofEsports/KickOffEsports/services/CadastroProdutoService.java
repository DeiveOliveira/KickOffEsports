package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Imagens;
import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.repositories.ListaDeImagensRepository;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class CadastroProdutoService {

    @Autowired
    ListaDeImagensRepository listaDeImagensRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    public void cadastrarProduto(Produto produto, MultipartFile[] imagens) {
        Produto pro = new Produto(produto.getNome(), produto.getAvaliacao(),
                produto.getDescricao(), produto.getPreco(), produto.getQuantidade());
        pro = produtoRepository.save(pro);

        // Caminho do diretório de destino
        String uploadDir = "src/main/resources/static/img/imagensDosProdutos";

        // Cria o diretório se ele não existir
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro ao criar o diretório de upload: " + e.getMessage());
            }
        }

        for (MultipartFile imagem : imagens) {
            try {
                String originalName = StringUtils.cleanPath(imagem.getOriginalFilename());
                String fileName = UUID.randomUUID().toString() +".jpg" ; // Nome do arquivo com extensão .jpg

                // Caminho completo para o arquivo
                String filePath = Paths.get(uploadDir, fileName).toString();

                // Salva a imagem no sistema de arquivos
                Files.copy(imagem.getInputStream(), Paths.get(filePath));

                // Salva a informação da imagem no banco de dados
                // Adiciona a string ao nome do arquivo ao salvar no banco de dados
                String dbFileName = fileName;
                Imagens img = new Imagens(pro, dbFileName);
                listaDeImagensRepository.save(img);
                pro.getImg().add(img);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro ao copiar a imagem: " + e.getMessage());
            }
        }
    }


}
