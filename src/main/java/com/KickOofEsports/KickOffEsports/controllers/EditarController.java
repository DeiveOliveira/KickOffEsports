package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.services.EditarService;
import com.KickOofEsports.KickOffEsports.services.exceptions.EmailDiferentesException;
import com.KickOofEsports.KickOffEsports.services.exceptions.RecursoNaoEncontradoException;
import com.KickOofEsports.KickOffEsports.services.exceptions.SenhaDiferenteException;
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
        usuario.ifPresent(u -> editar.addObject("usuario", u));
        editar.setViewName("Cadastro");
        System.out.println("pesquisou com sucesso o usuario do id: " + id);
        return editar;
    }

    @PutMapping(value = "editarUsuario/{id}")
    public ResponseEntity<?> editar(@PathVariable String id, @RequestBody Usuario usuario){
        try{
            usuario = service.atualizar(id, usuario);
            return ResponseEntity.ok().body(usuario);
        }
        catch (SenhaDiferenteException e){
            return ResponseEntity.badRequest().body("Senha diferente!");
        }
        catch (EmailDiferentesException e){
            return ResponseEntity.badRequest().body("Email não é alteravel");
        }
        catch (RecursoNaoEncontradoException e){
            return ResponseEntity.badRequest().body("Usuario não encontrado.");
        }
    }

}
