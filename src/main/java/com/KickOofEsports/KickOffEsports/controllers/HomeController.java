package com.KickOofEsports.KickOffEsports.controllers;


import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/home")
    public ModelAndView home(HttpSession session){
        ModelAndView model = new ModelAndView();
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        List<Produto> produtos = produtoRepository.findAll();
        if (!produtos.isEmpty()) {
            System.out.println("Encontrados " + produtos.size() + " produtos no banco de dados.");
        } else {
            System.out.println("Nenhum produto encontrado no banco de dados.");
        }
        model.addObject("produtos", produtos);
        if(cliente != null){

            model.addObject("usuarioLogado", cliente); // Adicione o usuário logado à modelo

        }
        return model;

    }

}
