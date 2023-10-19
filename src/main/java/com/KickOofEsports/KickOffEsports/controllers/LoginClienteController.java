package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.services.CadastrarClienteService;
import com.KickOofEsports.KickOffEsports.services.exceptions.SenhaDiferenteException;
import com.KickOofEsports.KickOffEsports.utils.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginClienteController {

    @Autowired
    CadastrarClienteService cadastrarClienteService;

    @GetMapping("loginCliente")
    public ModelAndView loginCliente(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("LoginCliente");
        return mv;
    }

    @PostMapping("/logarCliente")
    public String logar(@RequestBody LoginRequest loginRequest,
                                HttpSession session) {
        System.out.println("tentou logar");
        Cliente cliente = cadastrarClienteService.login(loginRequest.getEmail(), loginRequest.getSenha());
        if (cliente != null) {
            System.out.println("logou com sucesso o usuario " + cliente.getNomeCompleto());
            session.setAttribute("usuarioLogado", cliente);
            return "redirect:/home";
        } else {
            throw new SenhaDiferenteException();
        }
    }
}
