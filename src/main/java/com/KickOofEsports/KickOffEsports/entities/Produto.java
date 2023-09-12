package com.KickOofEsports.KickOffEsports.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table(name = "produtos")
@Entity(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private Double avaliacao;
    private String descricao;
    private Double preco;
    private Integer quantidade;

    @OneToMany(mappedBy = "produto")
    private Set<Imagens> img = new HashSet<>();

    public Produto(String nome, Double avaliacao, String descricao, Double preco, Integer quantidade) {
        this.nome = nome;
        this.avaliacao = avaliacao;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}
