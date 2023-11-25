package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.*;
import com.KickOofEsports.KickOffEsports.entities.enums.UserRole;
import com.KickOofEsports.KickOffEsports.repositories.PedidosRepository;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import com.KickOofEsports.KickOffEsports.repositories.UsuariosRepository;
import com.KickOofEsports.KickOffEsports.services.ClienteService;
import com.KickOofEsports.KickOffEsports.services.PedidoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PedidosRepository pedidosRepository;
    
    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "listaDePedidos")
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
            if(usuario.getRole() == UserRole.ESTOQUISTA){
                List<?> listaDePedidos = pedidoService.procurarTodosPedidos();
                mv.addObject("pedidos", listaDePedidos);
                return mv;
            } else{
                mv.setViewName("TelaPrincipal");
                return mv;
            }
        }else
        mv.setViewName("Home");
        return mv;
    }

    @GetMapping(value = "listaDePedidosCliente")
    public ModelAndView procurarTodosOsPedidosCliente(HttpSession session){
        ModelAndView mv = new ModelAndView();
        Object usuarioLogado = session.getAttribute("usuarioLogado");
        mv.setViewName("ListaDePedidosCliente");
        if(usuarioLogado instanceof Cliente){
            Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
            List<?> listaDePedidos = pedidoService.procurarPedidosPorUsuarioLogado(cliente.getId());
            mv.addObject("pedidos", listaDePedidos);
            return mv;
        }else if(usuarioLogado instanceof Usuario){
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
            if(usuario.getRole() == UserRole.ESTOQUISTA){
                List<?> listaDePedidos = pedidoService.procurarTodosPedidos();
                mv.addObject("pedidos", listaDePedidos);
                return mv;
            } else{
                mv.setViewName("TelaPrincipal");
                return mv;
            }
        }else
            mv.setViewName("Home");
        return mv;
    }

    @PutMapping (value = "atualizarStatusPedido/{id}")
    public ResponseEntity<?> atualizarStatusPedido(@PathVariable String id, @RequestBody AtualizacaoStatusDto atualizacaoStatusDto){
        Long idLong = Long.parseLong(id);
        Pedidos pedidos = pedidosRepository.getReferenceById(idLong);
        pedidoService.atualizarPedidos(idLong, atualizacaoStatusDto.getStatus());
        return ResponseEntity.ok().body(pedidos);
    }

    @PostMapping (value = "/salvarPedido")
    public ResponseEntity<?> salvarPedido(Double valorFrete, Integer parcelas, @RequestBody List<Produto> produto, @PathVariable String idCliente){
        List<Produto> produtos = new ArrayList<>();
        for (Produto p : produto) {
            Produto prod = produtoRepository.getReferenceById(p.getId());
            produtos.add(prod);
        }
        Pedidos pedido = pedidoService.cadastrarPedidos(valorFrete, parcelas, produtos, idCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);

    }

}
