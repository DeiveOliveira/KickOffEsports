package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Enderecos;
import com.KickOofEsports.KickOffEsports.entities.Pedidos;
import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.repositories.PedidosRepository;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Pedidos cadastrarPedidos(String formaDePagamento, Double valorFrete, Integer parcelas, String idEndereco, List<Produto> produtos,  String id) {
        List<Produto> produtos1 = converterDTOsParaEntidades(produtos);
        Cliente cliente = clienteRepository.getReferenceById(id);

        double valorTotal = 0;
        for (Produto produto : produtos1) {
            valorTotal += produto.getPreco() * produto.getQuantidade();
        }
        valorTotal += valorFrete;
        double valorParcela = valorTotal / parcelas;
        Pedidos pedido = new Pedidos(formaDePagamento ,valorParcela, valorTotal, valorFrete, parcelas, idEndereco, cliente);
        for (Produto produto : produtos1) {
            pedido.adicionarProduto(produto, produto.getQuantidade());
        }
        pedido = pedidosRepository.save(pedido);
        return pedido;
    }

    private List<Produto> converterDTOsParaEntidades(List<Produto> produtos) {
        List<Produto> produtos1 = new ArrayList<>();
        for (Produto produto : produtos) {
            Produto produto1 = produtoRepository.getReferenceById(produto.getId());
            produtos1.add(produto1);
        }
        return produtos;
    }

    public List<?> procurarTodosPedidos(){
        return pedidosRepository.findAll();
    }

    public List<?> procurarPedidosPorUsuarioLogado(String id){
        Cliente cliente = clienteRepository.getReferenceById(id);
        return cliente.getPedidosList();
    }

    @Transactional
    public Pedidos atualizarPedidos(Long id, String atualizacaoStatus){
        Pedidos pedidos = pedidosRepository.getReferenceById(id);
        atualizarStatus(pedidos, atualizacaoStatus);
        return pedidosRepository.save(pedidos);
    }

    @Transactional
    public void atualizarStatus(Pedidos pedidos ,String atualizacaoStatus){
        pedidos.setStatus(atualizacaoStatus);
    }
}
