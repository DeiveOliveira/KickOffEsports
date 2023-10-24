package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Imagens;
import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.services.VisualizarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class PossivelCompraController {
    @Autowired
    VisualizarService service;

    @GetMapping("/possivelCompra/{id}")
    public ModelAndView visualizar(@PathVariable String id, HttpSession session) {
        Optional<Produto> optionalProduto = service.procurarPorId(id);
        Cliente usuario = (Cliente) session.getAttribute("usuarioLogado");
        Produto produto = optionalProduto.get();
        ModelAndView visualizar = new ModelAndView();
        visualizar.setViewName("Visualizar");
        visualizar.addObject("produto", produto);
        System.out.println("pesquisou com sucesso o usuario do id: " + id);
        return visualizar;
    }
}

