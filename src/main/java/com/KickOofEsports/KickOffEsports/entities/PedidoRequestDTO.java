package com.KickOofEsports.KickOffEsports.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Getter
@Setter
public class PedidoRequestDTO {

    private String formaDePagamento;
    private Double valorFrete;
    private Integer parcelas;
    private String idEndereco;
    private List<Produto> produto;
}
