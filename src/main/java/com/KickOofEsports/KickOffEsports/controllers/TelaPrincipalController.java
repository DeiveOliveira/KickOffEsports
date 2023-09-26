package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TelaPrincipalController {

    @GetMapping("/telaPrincipal")
    public String telaPrincipal(HttpSession session){
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        if (usuario != null) {
            System.out.println("o : " + usuario.getNome() + " Logou com sucesso");
        } else {
            return "Login";
        }
        return "TelaPrincipal";
    }
}
