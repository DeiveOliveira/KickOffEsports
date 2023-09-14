package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.services.EditarService;
import com.KickOofEsports.KickOffEsports.services.VisualizarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class VisualizarController {

    @Autowired
    VisualizarService service;

    @GetMapping("/visualizar/{id}")
    public ModelAndView visualizar(@PathVariable String id) {
        Optional<Produto> optionalProduto = service.procurarPorId(id);

        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            ModelAndView visualizar = new ModelAndView();
            visualizar.setViewName("Visualizar");
            visualizar.addObject("produto", produto);
            System.out.println("pesquisou com sucesso o usuario do id: " + id);
            return visualizar;
        } else {
            // Trate o caso em que o Produto não foi encontrado
            // Você pode redirecionar para uma página de erro, por exemplo
            // Ou lançar uma exceção adequada
        }
        return null;
    }
}
