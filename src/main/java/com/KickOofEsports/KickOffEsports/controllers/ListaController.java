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
    public ModelAndView lista(@RequestParam(name = "nome", required = false) String nome) {
        List<Usuario> lista;
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("pesquisou com sucesso por " + nome);
        if (nome != null && !nome.isEmpty()) {
            // Realize a busca por nome com base no parâmetro 'nome'
            lista = repository.findByNome(nome);
            modelAndView.addObject("lista", lista);
            modelAndView.setViewName("ListaUsuarios");
        } else {
            lista = repository.findAll();
            modelAndView.addObject("lista", lista);
            modelAndView.setViewName("ListaUsuarios");
        }

        return modelAndView;
    }

    @PutMapping(value = "/atualizarStatus/{id}")
    public ResponseEntity<Usuario> atualizarStatus(@PathVariable String id){
        Usuario usuario = service.atualizarStatus(id);
        return ResponseEntity.ok().body(usuario);
    }
    /*
    @GetMapping(value = "/ListaDeUsuario/{nome}")
    public ResponseEntity<?> pesquisaPorNome(@PathVariable String nome){
        System.out.println("pesquisou com sucesso");
        List<Usuario> lista = service.pesquisaPorNome(nome);
        return ResponseEntity.ok().body(lista);
    }

     */
}
