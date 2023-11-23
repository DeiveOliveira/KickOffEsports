package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Pedidos;
import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.repositories.PedidosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Pedidos cadastrarPedidos(Double valorFrete, Integer parcelas, List<Produto> produtos, String id) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        double valorTotal = 0;
        for (Produto produto : produtos) {
            valorTotal += produto.getPreco() * produto.getQuantidade();
        }
        valorTotal += valorFrete;
        double valorParcela = valorTotal / parcelas;
        Pedidos pedido = new Pedidos(valorParcela, valorTotal, valorFrete, parcelas, cliente);
        for (Produto produto : produtos) {
            pedido.adicionarProduto(produto, produto.getQuantidade());
        }
        pedido = pedidosRepository.save(pedido);
        return pedido;
    }

    public List<?> procurarTodosPedidos(){
        return pedidosRepository.findAll();
    }

}
