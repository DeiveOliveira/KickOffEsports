package com.KickOofEsports.KickOffEsports.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagamentoController {

    @GetMapping("/pagamento")
    public ModelAndView Pagamento(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Pagamento");
        return mv;
    }

    @GetMapping("/resumoPedido")
    public ModelAndView pedido(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ResumoPedido");
        return mv;
    }

}
