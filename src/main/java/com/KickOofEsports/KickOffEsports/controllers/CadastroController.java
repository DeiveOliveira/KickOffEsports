package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.entities.enums.UserRole;
import com.KickOofEsports.KickOffEsports.repositories.UsuariosRepository;
import com.KickOofEsports.KickOffEsports.services.CadastrarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;

@Controller
@RequestMapping
public class CadastroController {

    //Ações do cadastrar Usuário

    @Autowired
    UsuariosRepository repository;
    @Autowired
    CadastrarService service;

    @GetMapping("/cadastro")
    public ModelAndView cadastro(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        ModelAndView cadastro = new ModelAndView();
        if (usuario == null){
            cadastro.setViewName("Login");
        }
        else if(usuario.getRole() == UserRole.ADMIN){
            cadastro.setViewName("Cadastro");
        }
        else {
            cadastro.setViewName("TelaPrincipal");
        }
        return cadastro;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
        if (repository.findByEmail(usuario.getEmail()) != null) {
            // Email já cadastrado, retorne uma mensagem de erro
            System.out.println("Email já cadastrado.");
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }

        if(repository.findByCpf(usuario.getCpf()) != null){
            System.out.println("Cpf ja cadastrado.");
            return ResponseEntity.badRequest().body("Cpf ja cadastrado.");
        }



        Usuario usuario1 = service.cadastrar(usuario);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuario1.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario1);
    }

    @GetMapping("/exibirImagens/{imagens}")
    @ResponseBody
    public byte[] retornarImagem(@PathVariable("imagens") String nomeImagem) throws IOException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+nomeImagem);
        File imagem = new File("src/main/resources/static/img/imagensDosProdutos/" +
                nomeImagem);
        if (nomeImagem != null || nomeImagem.trim().length() > 0){
            return Files.readAllBytes(imagem.toPath());
        }
        return null;
    }
}
