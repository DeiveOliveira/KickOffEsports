package com.KickOofEsports.KickOffEsports.entities.ids;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ListaDeImagensID implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Produto produto;


}