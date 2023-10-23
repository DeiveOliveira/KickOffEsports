package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Produto;
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
        ModelAndView possivelCompra = new ModelAndView();
        Optional<Produto> optionalProduto = service.procurarPorId(id);
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        if (cliente != null) {
            possivelCompra.setViewName("PossivelCompra");
            possivelCompra.addObject("produto", optionalProduto);
            System.out.println("pesquisou com sucesso o usuario do id: " + id);
            return possivelCompra;
        } else {
            possivelCompra.setViewName("LoginCliente");
        }
        return possivelCompra;
    }
}

