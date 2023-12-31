package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Enderecos;
import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.repositories.EnderecosRepository;
import com.KickOofEsports.KickOffEsports.services.ClienteService;
import com.KickOofEsports.KickOffEsports.services.EnderecosServices;
import com.KickOofEsports.KickOffEsports.services.exceptions.EmailDiferentesException;
import com.KickOofEsports.KickOffEsports.services.exceptions.RecursoNaoEncontradoException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class EnderecoController {

    @Autowired
    private EnderecosServices service;

    @Autowired
    private EnderecosRepository enderecosRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("Endereco")
    public ModelAndView endereco(HttpSession session){
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuarioLogado", cliente);
        mv.setViewName("Endereco");
        return mv;
    }
    @GetMapping("EnderecoCompra/{id}")
    public ModelAndView enderecoCompra(HttpSession session){
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        ModelAndView mv = new ModelAndView();
        mv.addObject("usuarioLogado", cliente);
        mv.setViewName("EnderecoCompra");
        return mv;
    }

    @PostMapping("/cadastrarEndereco")
    public ResponseEntity<?> cadastrarProduto(@RequestBody Enderecos enderecos, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        Enderecos enderecos1 = service.cadastrar(enderecos, cliente.getId());
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(cliente.getId())
                .buildAndExpand(enderecos1.getId()).toUri();

        Cliente cl = new Cliente();
        cl = clienteRepository.getReferenceById(cliente.getId());
        return ResponseEntity.created(uri).body(enderecos1);
    }

    @GetMapping("/ediEndereco/{id}")
    public ModelAndView editarEndereco(@PathVariable String id, HttpSession session){
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        Enderecos endereco = enderecosRepository.getReferenceById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("endereco", endereco);
        mv.addObject("usuarioLogado", cliente);
        mv.setViewName("Endereco");
        return mv;
    }

    @PutMapping(value = "/editarEndereco/{id}")
    public ResponseEntity<?> editar(@PathVariable String id, @RequestBody Enderecos endereco, HttpSession session){
        try{
            endereco = service.atualizar(id, endereco);
            return ResponseEntity.ok().body(endereco);
        }
        catch (RecursoNaoEncontradoException e){
            return ResponseEntity.badRequest().body("Endereço não encontrado.");
        }
    }

    @GetMapping("/listaEnderecos/{id}")
    @ResponseBody
    public ModelAndView listaEnderecos(@PathVariable String id){
        ModelAndView mv = new ModelAndView();
        Optional<?> objOptional = clienteService.procurarPorId(id);
        if(objOptional.isPresent()){
            Cliente cliente = (Cliente) objOptional.get();
            List<Enderecos> listaDeEndereco = cliente.getEnderecosList();

            // Filtrar apenas os endereços ativos
            List<Enderecos> listaDeEnderecoAtivo = listaDeEndereco.stream()
                    .filter(Enderecos::isAtivo)
                    .collect(Collectors.toList());

            mv.addObject("endereco", listaDeEnderecoAtivo);
            mv.setViewName("ListaDeEnderecos");
            return mv;
        }
        else{
            return new ModelAndView();
        }
    }

    @GetMapping("/enderecoEntrega/{id}")
    @ResponseBody
    public ModelAndView enderecoEntrega(@PathVariable String id){
        ModelAndView mv = new ModelAndView();
        Optional<?> objOptional = clienteService.procurarPorId(id);
        if(objOptional.isPresent()){
            Cliente cliente = (Cliente) objOptional.get();
            List<Enderecos> listaDeEndereco = cliente.getEnderecosList();

            // Filtrar apenas os endereços ativos
            List<Enderecos> listaDeEnderecoAtivo = listaDeEndereco.stream()
                    .filter(Enderecos::isAtivo)
                    .collect(Collectors.toList());

            mv.addObject("endereco", listaDeEnderecoAtivo);
            mv.setViewName("EnderecoEntrega");
            return mv;
        }
        else{
            return new ModelAndView();
        }
    }
    @PutMapping(value = "/ativarDesativar/{id}")
    public ResponseEntity<Enderecos> ativarDesativar(@PathVariable String id){
        System.out.println("chegou aqui");
        Enderecos enderecos = clienteService.atualizarStatus(id);
        return ResponseEntity.ok().body(enderecos);
    }

    @PutMapping(value = "/atualizarEnderecoPadrao/{id}")
    public ResponseEntity<Enderecos> atualizarEnderecoPadrao(@PathVariable String id){
        System.out.println("chegou aqui");
        Enderecos enderecos = clienteService.atualizarEnderecoPadrao(id);
        return ResponseEntity.ok().body(enderecos);
    }

    @RequestMapping("/api/enderecos")
    @GetMapping
    public ResponseEntity<List<Enderecos>> obterTodosEnderecos(HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        try {
            List<Enderecos> enderecos = service.procurarTodosEnderecos(cliente.getId());
            return new ResponseEntity<>(enderecos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/procurarEnderecoPorId/{id}")
    public ResponseEntity<?> procurarEnderecoPorId(@PathVariable String id){
            Enderecos enderecos = service.procurarEnderecoPorId(id);
            return ResponseEntity.ok().body(enderecos);
    }
}
