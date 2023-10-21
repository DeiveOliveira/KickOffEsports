package com.KickOofEsports.KickOffEsports.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "enderecos")
@Entity(name = "enderecos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Enderecos {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    private boolean ativo;

    @OneToOne(mappedBy = "enderecoCobranca")
    private Cliente clienteCobranca;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Enderecos(String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String uf, Cliente cliente) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cliente = cliente;
    }

    public Enderecos(String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }
}
