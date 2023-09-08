package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.UsuariosRepository;
import com.KickOofEsports.KickOffEsports.services.CadastrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping
public class CadastroController {

    @Autowired
    UsuariosRepository repository;
    @Autowired
    CadastrarService service;

    @GetMapping("/cadastro")
    public ModelAndView cadastro() {
        ModelAndView cadastro = new ModelAndView();
        cadastro.setViewName("Cadastro");
        return cadastro;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
            if (repository.findByEmail(usuario.getEmail()) != null) {
                return ResponseEntity.badRequest().build();
            }
            Usuario usuario1 = service.cadastrar(usuario);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(usuario1.getId()).toUri();
            return ResponseEntity.created(uri).body(usuario1);
        }
}
