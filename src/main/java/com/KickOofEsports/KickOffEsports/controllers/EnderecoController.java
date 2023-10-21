package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Enderecos;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.services.EnderecosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
public class EnderecoController {

    @Autowired
    private EnderecosServices service;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("Endereco/{id}")
    public ModelAndView endereco(@PathVariable String id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Endereco");
        return mv;
    }

    @PostMapping("/cadastrarEndereco/{id}")
    public ResponseEntity<?> cadastrarProduto(@RequestBody Enderecos enderecos, @PathVariable String id) {
        Enderecos enderecos1 = service.cadastrar(enderecos, id);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(enderecos1.getId()).toUri();

        Cliente cl = new Cliente();
        cl = clienteRepository.getById(id);
        if(cl.getEnderecoCobranca() == null){
            cl.setEnderecoCobranca(enderecos1);
            clienteRepository.save(cl);
        }
        return ResponseEntity.created(uri).body(enderecos1);
    }
}
