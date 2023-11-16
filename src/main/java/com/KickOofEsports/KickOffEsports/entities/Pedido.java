package com.KickOofEsports.KickOffEsports.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "pedidos")
@Entity(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String formaDePagamento;
    private Double valorTotal;
    private Integer parcelas;
    private String status;
    private Double valorParcela;
    @JoinColumn(name = "cliente.id")
    private Cliente cliente;

    @OneToMany
    private List<Produto> listaDeProdutos = new ArrayList<>();

    public Pedido(String formaDePagamento, Double valorTotal, Integer parcelas, String status, Cliente cliente,  List<Produto> listaDeProdutos) {
        this.formaDePagamento = formaDePagamento;
        this.valorTotal = valorTotal;
        this.parcelas = parcelas;
        this.status = status;
        this.cliente = cliente;
        this.listaDeProdutos = listaDeProdutos;
        this.valorParcela = valorTotal / parcelas;
    }

    public Pedido(String formaDePagamento, Double valorTotal, Integer parcelas, String status, List<Produto> listaDeProdutos) {
        this.formaDePagamento = formaDePagamento;
        this.valorTotal = valorTotal;
        this.parcelas = parcelas;
        this.status = status;
        this.listaDeProdutos = listaDeProdutos;
        this.valorParcela = valorTotal / parcelas;
    }
}
