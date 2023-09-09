package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.UsuariosRepository;
import com.KickOofEsports.KickOffEsports.services.ListaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping
public class ListaController {

    @Autowired
    UsuariosRepository repository;

    @Autowired
    ListaService service;

    @GetMapping("/listaDeUsuario")
    public ModelAndView lista(){
        List<Usuario> lista = this.repository.findAll();
        ModelAndView listaDeUsuario = new ModelAndView();
        listaDeUsuario.addObject("lista", lista);
        listaDeUsuario.setViewName("ListaUsuarios");
        return listaDeUsuario;
    }

    @PutMapping(value = "/atualizarStatus/{id}")
    public ResponseEntity<Usuario> atualizarStatus(@PathVariable String id, @RequestBody Usuario usuario){
        usuario = service.atualizarStatus(id);
        return ResponseEntity.ok().body(usuario);
    }

}
