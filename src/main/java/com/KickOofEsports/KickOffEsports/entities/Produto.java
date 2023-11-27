package com.KickOofEsports.KickOffEsports.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    private Boolean status;

    @JsonManagedReference
    @OneToMany(mappedBy = "produto", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Fetch(value = FetchMode.JOIN)
    @Setter(value = AccessLevel.NONE) //Desabilitando o seter do LOMBOK
    private List<Imagens> img = new ArrayList<>();

    public Produto(String nome, Double avaliacao, String descricao, Double preco, Integer quantidade) {
        this.nome = nome;
        this.avaliacao = avaliacao;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = 1;
        this.status = true;
    }

    public Produto(String id) {
        this.id = id;
    }

    public void setImagens(List<Imagens> ListaImagens){
        for(Imagens mg: ListaImagens){
            mg.setProduto(this);
        }
        this.img = ListaImagens;
    }


}
