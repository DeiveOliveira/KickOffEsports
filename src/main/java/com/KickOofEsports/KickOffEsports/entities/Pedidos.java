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
    @GeneratedValue (strategy = GenerationType.AUTO)
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

    @Column
    @ElementCollection
    private List<Produto> produtoList = new ArrayList<>();

    public Pedidos(Double valorParcela, Double valorTotal, Double valorFrete, Integer parcelas, Cliente clientePedido, List<Produto> produtoList) {
        this.valorParcela = valorParcela;
        this.valorTotal = valorTotal;
        this.valorFrete = valorFrete;
        this.parcelas = parcelas;
        this.clientePedido = clientePedido;
        this.produtoList = produtoList;
    }
}
