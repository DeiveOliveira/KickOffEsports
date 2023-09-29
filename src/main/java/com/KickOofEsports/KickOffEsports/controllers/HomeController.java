package com.KickOofEsports.KickOffEsports.controllers;


import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/")
    public String home(Model model){

        List<Produto> produtos = produtoRepository.findAll();

        if (!produtos.isEmpty()) {
            System.out.println("Encontrados " + produtos.size() + " produtos no banco de dados.");
        } else {
            System.out.println("Nenhum produto encontrado no banco de dados.");
        }


        model.addAttribute("produtos", produtos);

        return "Home";

    }

}
