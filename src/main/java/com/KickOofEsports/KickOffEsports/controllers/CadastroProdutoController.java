package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import com.KickOofEsports.KickOffEsports.services.CadastroProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class CadastroProdutoController {

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

    @SneakyThrows
    @PostMapping("/cadastrarProduto")
    public ModelAndView cadastrarProduto(@RequestPart("produto") String produtoStr, @RequestPart("file") MultipartFile[] imagens) {
        ObjectMapper mapper = new ObjectMapper();
        Produto produto = mapper.readValue(produtoStr, Produto.class);
        for (MultipartFile ima: imagens){
            System.out.println(ima.getName());
        }
        try {
            service.cadastrarProduto(produto, imagens);
            return new ModelAndView("ListaProduto");
        }catch (Exception e) {
            System.out.println("Erro ao tentar salvar");
            return new ModelAndView("ListaProduto");
        }
    }



}