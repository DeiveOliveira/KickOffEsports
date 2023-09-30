package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import com.KickOofEsports.KickOffEsports.services.CadastroProdutoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @PostMapping("/cadastrarProduto")
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        Produto produto1 = service.cadastrarProduto(produto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(produto1.getId()).toUri();
        return ResponseEntity.created(uri).body(produto1);
    }


//    @PostMapping("/produto")
//    public ModelAndView cadastroProduto(@ModelAttribute Produto produto, @ModelAttribute Imagens img, @RequestParam("file")MultipartFile imagem){
//        ModelAndView mv = new ModelAndView("cadastroProduto");
//        mv.addObject("produto", produto);
//
//        try {
//            if (UploadImagens.fazerUploadImagens(imagem)){
//                img.setUrl(imagem.getOriginalFilename());
//                img.setProduto(produto);
//            }
//            return new ModelAndView("listaProduto");
//        }catch (Exception e){
//            System.out.println("Erro ao salvar imagem");
//            return mv;
//        }
//    }

}