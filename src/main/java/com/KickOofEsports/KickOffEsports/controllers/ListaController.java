package com.KickOofEsports.KickOffEsports.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class ListaController {

    @GetMapping("/listaDeUsuario")
    public ModelAndView lista(){
        ModelAndView listaDeUsuario = new ModelAndView();
        listaDeUsuario.setViewName("ListaUsuarios");
        return listaDeUsuario;
    }
}
