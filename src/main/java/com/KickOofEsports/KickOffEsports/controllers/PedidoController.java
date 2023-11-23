package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.repositories.PedidosRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private PedidosRepository pedidosRepository;

    @GetMapping(value = "listaDePedidos" )
    public ModelAndView procurarTodosOsPedidos(HttpSession session){
        ModelAndView mv = new ModelAndView();
        List<?> listaDePedidos = pedidosRepository.findAll();
        mv.addObject("pedidos", listaDePedidos);
        mv.setViewName("ListaDePedidos");
        return mv;
    }
}
