package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import com.KickOofEsports.KickOffEsports.services.CadastroProdutoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping
public class CadastroProdutoController {

    private static String diretorio = "src/main/resources/static/img/imagensDosProdutos";


    @Autowired
    CadastroProdutoService service;

    @Autowired
    ProdutoRepository repository;

    @GetMapping("/produto")
    public ModelAndView cadastroProduto(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        ModelAndView cadastroProduto = new ModelAndView();
        if(usuario != null){
            cadastroProduto.setViewName("CadastroProduto");
        }
        else{
            cadastroProduto.setViewName("Login");
        }
        return cadastroProduto;
    }

    @PostMapping("/cadastrarProduto")
    public ResponseEntity<Produto> cadastrarProduto(
            @ModelAttribute Produto produto,
            @RequestParam("file") MultipartFile file
    ) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Arquivo não pode ser nulo ou vazio");
        }

        try{
            String nomeImagem = Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "");
            Path path = Paths.get(diretorio, nomeImagem);

            if (!Files.exists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }

            Files.write(path, file.getBytes());

            Produto produto1 = service.cadastrarProduto(produto);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(produto1.getId()).toUri();
            return ResponseEntity.created(uri).body(produto1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
