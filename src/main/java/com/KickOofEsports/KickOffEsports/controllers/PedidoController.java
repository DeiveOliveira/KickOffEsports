package com.KickOofEsports.KickOffEsports.controllers;

import com.KickOofEsports.KickOffEsports.entities.*;
import com.KickOofEsports.KickOffEsports.entities.enums.UserRole;
import com.KickOofEsports.KickOffEsports.repositories.EnderecosRepository;
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
import java.util.Optional;

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

    @Autowired
    private EnderecosRepository enderecosRepository;

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
    public ResponseEntity<?> atualizarStatusPedido(@PathVariable Long id, @RequestBody AtualizacaoStatusDto atualizacaoStatusDto){
        Pedidos pedidos = pedidosRepository.getReferenceById(id);
        pedidoService.atualizarPedidos(id, atualizacaoStatusDto.getStatus());
        return ResponseEntity.ok().body(pedidos);
    }

    @PostMapping(value = "/salvarPedido")
    public ResponseEntity<?> salvarPedido(@RequestBody PedidoRequestDTO pedidoRequestDTO, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        List<Produto> produtos = new ArrayList<>();
        for (Produto p : pedidoRequestDTO.getProduto()) {
            Produto prod = produtoRepository.getReferenceById(p.getId());
            produtos.add(prod);
        }

        System.out.println("forma de pagamento é: " + pedidoRequestDTO.getFormaDePagamento() );
        for (Produto produto : pedidoRequestDTO.getProduto()){
            System.out.println("nome dos produtos: " + produto.getNome());
        }
        Pedidos pedido = pedidoService.cadastrarPedidos(
                pedidoRequestDTO.getFormaDePagamento(),
                pedidoRequestDTO.getValorFrete(),
                pedidoRequestDTO.getParcelas(),
                pedidoRequestDTO.getIdEndereco(),
                produtos,
                cliente.getId()
        );

        return ResponseEntity.ok().body(pedido);
    }

    @GetMapping(value = "/meuPedido/{id}")
    public ModelAndView procuraPedido(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        Optional<Pedidos> objPedidos = pedidosRepository.findById(id);
        Pedidos objPedido = pedidosRepository.getReferenceById(id);
        Optional<Enderecos> objEndereco = enderecosRepository.findById(objPedido.getIdEndereco());
        Pedidos pedidos = objPedidos.get();
        Enderecos endereco = objEndereco.get();
        mv.addObject("pedido", pedidos);
        mv.addObject("endereco", endereco);
        mv.setViewName("DetalhesPedido");
        return mv;
    }



}
