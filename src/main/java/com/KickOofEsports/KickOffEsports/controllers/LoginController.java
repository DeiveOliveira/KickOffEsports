package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.services.LoginService;
import com.KickOofEsports.KickOffEsports.services.exceptions.SenhaDiferenteException;
import com.KickOofEsports.KickOffEsports.utils.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    LoginService service;

    @GetMapping("/login")
    public String login(){

        return "Login";
    }

    @PostMapping("/logar")
    public String logar(@RequestBody LoginRequest loginRequest,
                        HttpSession session){
        System.out.println("tentou logar");
        Usuario usuario = service.login(loginRequest.getEmail(), loginRequest.getSenha());
        if(usuario != null){
            System.out.println("logou com sucesso!");
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/telaPrincipal";
        }else{
            throw new SenhaDiferenteException();
        }
    }

}




