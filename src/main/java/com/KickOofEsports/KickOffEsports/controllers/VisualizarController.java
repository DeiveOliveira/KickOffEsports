package com.KickOofEsports.KickOffEsports.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VisualizarController {

    @GetMapping("/visualizar")
    public ModelAndView visualizar(){
        ModelAndView visualizar = new ModelAndView();
        visualizar.setViewName("Visualizar");
        return visualizar;
    }
}
