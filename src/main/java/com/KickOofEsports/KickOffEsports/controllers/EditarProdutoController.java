package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.services.EditarProdutoService;
import com.KickOofEsports.KickOffEsports.services.exceptions.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class EditarProdutoController {

    @Autowired
    EditarProdutoService service;

    @GetMapping(value = "/editarProduto/{id}")
    public ModelAndView editar(@PathVariable String id){
        Optional<Produto> produto = service.procurarPorId(id);
        ModelAndView editar = new ModelAndView();
        produto.ifPresent(u -> editar.addObject("produto", u));
        editar.setViewName("CadastroProduto");
        System.out.println("pesquisou com sucesso o usuario do id: " + id);
        return editar;
    }

    @PutMapping(value = "produtoEditado/{id}")
    public ResponseEntity<?> editar(@PathVariable String id, @RequestBody Produto produto){
        try{
            produto = service.atualizar(id, produto);
            return ResponseEntity.ok().body(produto);
        }
        catch (RecursoNaoEncontradoException e){
            return ResponseEntity.badRequest().body("Produto não encontrado.");
        }
    }

}
