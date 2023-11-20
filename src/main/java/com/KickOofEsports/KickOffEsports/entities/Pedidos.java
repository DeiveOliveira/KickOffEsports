package com.KickOofEsports.KickOffEsports.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "pedidos")
@Entity(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String formaDePagamento;
    private Double valorParcela;
    private Double valorTotal;
    private Double valorFrete;
    private Integer parcelas;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente clientePedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProduto> produtos = new ArrayList<>();

    public Pedidos(Double valorParcela, Double valorTotal, Double valorFrete, Integer parcelas, Cliente clientePedido) {
        this.valorParcela = valorParcela;
        this.valorTotal = valorTotal;
        this.valorFrete = valorFrete;
        this.parcelas = parcelas;
        this.clientePedido = clientePedido;
    }

    public void adicionarProduto(Produto produto, Integer quantidade) {
        PedidoProduto pedidoProduto = new PedidoProduto();
        pedidoProduto.setPedido(this);
        pedidoProduto.setProduto(produto);
        pedidoProduto.setQuantidade(quantidade);
        this.produtos.add(pedidoProduto);
    }
}
