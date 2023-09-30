package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.UsuariosRepository;
import com.KickOofEsports.KickOffEsports.services.ListaService;
import jakarta.servlet.http.HttpSession;
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
    public ModelAndView lista(@RequestParam(name = "nome", required = false) String nome, HttpSession session) {
        List<Usuario> lista;
        ModelAndView modelAndView = new ModelAndView();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null){
            modelAndView.setViewName("Login");

        } else if (usuario.getStatus().equals("ADMIN")) {
            System.out.println("Usuário é um administrador.");
            System.out.println("pesquisou com sucesso por " + nome);
            if (nome != null && !nome.isEmpty()) {
                lista = repository.findByNome(nome);
            } else {
                lista = repository.findAll();
            }
            modelAndView.addObject("lista", lista);
            modelAndView.setViewName("ListaUsuarios");
        }
        else {
            modelAndView.setViewName("telaPrincipal");
        }


        return modelAndView;
    }

    @PutMapping(value = "/atualizarStatus/{id}")
    public ResponseEntity<Usuario> atualizarStatus(@PathVariable String id){
        Usuario usuario = service.atualizarStatus(id);
        return ResponseEntity.ok().body(usuario);
    }

}
