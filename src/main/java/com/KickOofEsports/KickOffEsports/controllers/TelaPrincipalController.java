package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/CarrinhoDeCompra/{id}")
    public ModelAndView CarrinhoDeCompra(@PathVariable String id, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        if (cliente != null) {
            mv.addObject("usuarioLogado", cliente);
            mv.addObject("enderecosUsuarioLogado", cliente.getEnderecosList());
        }
        mv.setViewName("CarrinhoDeCompra");
        return mv;
    }

    @GetMapping("/CarrinhoDeCompra")
    public ModelAndView CarrinhoDeCompraDeslogado(@PathVariable String id, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        if (cliente != null) {
            mv.addObject("usuarioLogado", cliente);
            mv.addObject("enderecosUsuarioLogado", cliente.getEnderecosList());
        }
        mv.setViewName("CarrinhoDeCompra");
        return mv;
    }



}
