package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.services.VisualizarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PossivelCompraController {

    @Autowired
    VisualizarService service;

    @GetMapping("/possivelCompra/{id}")
    public ModelAndView possivelCompra(@PathVariable String id, HttpSession session) {
        Optional<Produto> optionalProduto = service.procurarPorId(id);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        ModelAndView cadastroProduto = new ModelAndView();
        if(usuario != null){
            if (optionalProduto.isPresent()) {
                Produto produto = optionalProduto.get();
                ModelAndView PossivelCompra = new ModelAndView();
                PossivelCompra.setViewName("PossivelCompra");
                PossivelCompra.addObject("produto", produto);
                System.out.println("pesquisou com sucesso o usuario do id: " + id);
                return PossivelCompra;
            } else {
                // Trate o caso em que o Produto não foi encontrado
                // Você pode redirecionar para uma página de erro, por exemplo
                // Ou lançar uma exceção adequada
            }
        }
        else{
            cadastroProduto.setViewName("Login");
        }

        return null;
    }
}
