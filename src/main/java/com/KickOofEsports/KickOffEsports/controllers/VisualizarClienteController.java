package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.services.CadastrarClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class VisualizarClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CadastrarClienteService cadastrarClienteService;

    @GetMapping(value = "/visualizarCliente/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable String id){
        Cliente obj = cadastrarClienteService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
