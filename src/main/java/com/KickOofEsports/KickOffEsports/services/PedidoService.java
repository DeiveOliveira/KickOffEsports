package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Pedido;
import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    @Transactional
    public Object cadastrarPedido(Pedido pedido, String id){
        Cliente cliente = clienteRepository.getReferenceById(id);
        List<Produto> listaProduto = pedido.getListaDeProdutos();
        Pedido pedido1 = new Pedido(pedido.getFormaDePagamento(), pedido.getValorTotal(), pedido.getParcelas(), pedido.getStatus(), cliente, listaProduto);
        System.out.println("Lista de produtos antes de salvar o pedido: ");
        for(Produto produto : listaProduto) {
            System.out.println("nome" + produto.getNome());
        }
        pedidoRepository.save(pedido1);
        cliente.getProdutos().add(pedido1);
        clienteRepository.save(cliente);
        return pedido1;
    }
}
