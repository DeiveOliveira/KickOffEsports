package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Imagens;
import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import com.KickOofEsports.KickOffEsports.services.CadastroProdutoService;
import com.KickOofEsports.KickOffEsports.utils.UploadImagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
public class CadastroProdutoController {

    @Autowired
    CadastroProdutoService service;

    @Autowired
    ProdutoRepository repository;

    @GetMapping("/produto")
    public ModelAndView cadastroProduto() {
        ModelAndView cadastroProduto = new ModelAndView();
        cadastroProduto.setViewName("cadastroProduto");
        return cadastroProduto;
    }

   /* @PostMapping("/cadastrarProduto")
    public ResponseEntity<?> cadastrarProduto(@RequestBody Produto produto){
        Produto produto1 = service.cadastrarProduto(produto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(produto1.getId()).toUri();
        return ResponseEntity.created(uri).body(produto1);
    }*/

    @PostMapping("/produto")
    public ModelAndView cadastroProduto(@ModelAttribute Produto produto, @ModelAttribute Imagens img, @RequestParam("file")MultipartFile imagem){
        ModelAndView mv = new ModelAndView("cadastroProduto");
        mv.addObject("produto", produto);

        try {
            if (UploadImagens.fazerUploadImagens(imagem)){
                img.setUrl(imagem.getOriginalFilename());
                img.setProduto(produto);
            }
            return new ModelAndView("listaProduto");
        }catch (Exception e){
            System.out.println("Erro ao salvar imagem");
            return mv;
        }
    }

}
