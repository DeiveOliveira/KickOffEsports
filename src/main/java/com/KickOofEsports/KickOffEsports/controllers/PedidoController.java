package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.PedidosRepository;
import com.KickOofEsports.KickOffEsports.repositories.UsuariosRepository;
import com.KickOofEsports.KickOffEsports.services.ClienteService;
import com.KickOofEsports.KickOffEsports.services.PedidoService;
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
    private ClienteService clienteService;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PedidosRepository pedidosRepository;
    
    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "listaDePedidos" )
    public ModelAndView procurarTodosOsPedidos(HttpSession session){
        ModelAndView mv = new ModelAndView();
        Object usuarioLogado = session.getAttribute("usuarioLogado");
        mv.setViewName("ListaDePedidos");
        if(usuarioLogado instanceof Cliente){
            Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
            List<?> listaDePedidos = pedidoService.procurarPedidosPorUsuarioLogado(cliente.getId());
            mv.addObject("pedidos", listaDePedidos);
            return mv;
        }else if(usuarioLogado instanceof Usuario){
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            List<?> listaDePedidos = pedidoService.procurarTodosPedidos();
            mv.addObject("pedidos", listaDePedidos);
            return mv;
        }else
        mv.setViewName("Home");
        return mv;
    }
}