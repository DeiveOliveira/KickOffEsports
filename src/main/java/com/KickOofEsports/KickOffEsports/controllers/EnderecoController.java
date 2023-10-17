package com.KickOofEsports.KickOffEsports.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EnderecoController {

    @GetMapping("Endereco")
    public ModelAndView endereco(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Endereco");
        return mv;
    }
}
