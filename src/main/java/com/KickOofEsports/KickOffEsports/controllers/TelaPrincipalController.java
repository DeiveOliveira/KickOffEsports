package com.KickOofEsports.KickOffEsports.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TelaPrincipalController {

    @GetMapping("/telaPrincipal")
    public String telaPrincipal(){
        return "TelaPrincipal";
    }
}
