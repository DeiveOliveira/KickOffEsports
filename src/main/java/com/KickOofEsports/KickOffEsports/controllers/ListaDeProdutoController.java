package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import com.KickOofEsports.KickOffEsports.services.ListaDeProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ListaDeProdutoController {

    @Autowired
    ProdutoRepository repository;

    @Autowired
    ListaDeProdutoService service;

    @GetMapping("/listaDeProduto")
    public ModelAndView lista(@RequestParam(name = "descricao", required = false) String descricao) {
        List<Produto> lista;
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("pesquisou com sucesso por " + descricao);
        if (descricao != null && !descricao.isEmpty()) {
            lista = repository.findByDescricaoContaining(descricao);
        } else {
            lista = repository.findAll();
        }
        modelAndView.addObject("lista", lista);
        modelAndView.setViewName("ListaProduto");

        return modelAndView;
    }

    @PutMapping(value = "/atualizarStatusProduto/{id}")
    public ResponseEntity<Produto> atualizarStatusProduto(@PathVariable String id){
        System.out.println("chegou aqui");
        Produto produto = service.atualizarStatus(id);
        return ResponseEntity.ok().body(produto);
    }


}
