package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.services.EditarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class EditarController {

    @Autowired
    EditarService service;

    @GetMapping(value = "/editar/{id}")
    public ModelAndView editar(@PathVariable String id){
        Optional<Usuario> usuario = service.procurarPorId(id);
        ModelAndView editar = new ModelAndView();
        editar.addObject("usuario", usuario);
        editar.setViewName("Cadastro");
        System.out.println("pesquisou com sucesso o usuario do id: " + id);
        return editar;
    }

    @PutMapping(value = "editarUsuario/{id}")
    public ResponseEntity<?> editar(@PathVariable String id, @RequestBody Usuario usuario){
        usuario = service.atualizar(id, usuario);
        return ResponseEntity.ok().body(usuario);
    }

}
