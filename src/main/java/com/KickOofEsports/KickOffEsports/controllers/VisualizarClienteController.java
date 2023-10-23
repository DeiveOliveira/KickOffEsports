package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.services.ClienteService;
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
    private ClienteService clienteService;

    @GetMapping(value = "/visualizarCliente/{id}")
    public ResponseEntity<Optional<?>> findById(@PathVariable String id){
        Optional<?> obj = clienteService.procurarPorId(id);
        return ResponseEntity.ok().body(obj);
    }
}
