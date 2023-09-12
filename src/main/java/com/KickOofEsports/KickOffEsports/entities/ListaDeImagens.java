package com.KickOofEsports.KickOffEsports.entities;

import com.KickOofEsports.KickOffEsports.entities.ids.ListaDeImagensID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ListaDeImagens {

    @EmbeddedId
    private ListaDeImagensID id = new ListaDeImagensID();

    private String url;

    public ListaDeImagens(Produto produto, String url){
        id.setProduto(produto);
        this.url = url;
    }

    @JsonIgnore
    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }
}
