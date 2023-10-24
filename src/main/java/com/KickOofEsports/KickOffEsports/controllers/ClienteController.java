package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.services.ClienteService;
import com.KickOofEsports.KickOffEsports.services.EditarService;
import com.KickOofEsports.KickOffEsports.services.exceptions.EmailDiferentesException;
import com.KickOofEsports.KickOffEsports.services.exceptions.RecursoNaoEncontradoException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @Autowired
    ClienteService service;

    @GetMapping("/CadastroCliente")
    public ModelAndView cadastroCliente(HttpSession session){
        ModelAndView mv = new ModelAndView();
        Cliente cliente = (Cliente) session.getAttribute("clienteLogado");
        if(cliente != null){
            mv.setViewName("Home");
        }else{
            mv.setViewName("CadastroCliente");
        }
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
            session.setAttribute("clienteLogado", usuario1);
        }
        return ResponseEntity.created(uri).body(usuario1);
    }

    @GetMapping(value = "/editarCli")
    public ModelAndView editarCliente(HttpSession session){
        Cliente sessionCliente = (Cliente) session.getAttribute("usuarioLogado");
        Optional<?> optionalCliente = service.procurarPorId(sessionCliente.getId());
        ModelAndView editar = new ModelAndView();
        Cliente cliente = (Cliente) optionalCliente.get();
        editar.addObject("cliente", cliente);
        if (cliente.getDataNascimento() != null) {
            LocalDate dataNascimento = LocalDate.parse(cliente.getDataNascimento());
            editar.addObject("dataNascimento", dataNascimento);
        }
        editar.setViewName("CadastroCliente");
        System.out.println("pesquisou com sucesso o usuario do id: " + sessionCliente.getId());
        return editar;
    }

    @PutMapping(value = "editarCliente/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable String id, @RequestBody Cliente cliente){
        try{
            cliente = service.atualizar(id, cliente);
            return ResponseEntity.ok().body(cliente);
        }
        catch (EmailDiferentesException e){
            return ResponseEntity.badRequest().body("Email não é alteravel");
        }
        catch (RecursoNaoEncontradoException e){
            return ResponseEntity.badRequest().body("Usuario não encontrado.");
        }
    }
}
