package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.services.CadastrarClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
public class CadastrarClienteController {

    @Autowired
    ClienteRepository repository;

    @Autowired
    CadastrarClienteService service;

    @GetMapping("/CadastroCliente")
    public ModelAndView cadastroCliente(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("CadastroCliente");
        return mv;
    }

    @PostMapping("/CadastrarCliente")
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente, HttpSession session){
        System.out.println("tentou cadastrar o cliente");
        if (repository.findByEmail(cliente.getEmail()) != null) {
            System.out.println("Email já cadastrado.");
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }

        if(repository.findByCpf(cliente.getCpf()) != null){
            System.out.println("Cpf ja cadastrado.");
            return ResponseEntity.badRequest().body("Cpf ja cadastrado.");
        }

        Cliente usuario1 = service.cadastrar(cliente);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuario1.getId()).toUri();

        if(repository.findByEmail(cliente.getEmail()) != null){
            session.setAttribute("usuarioLogado", usuario1);
        }
        return ResponseEntity.created(uri).body(usuario1);
    }
}
