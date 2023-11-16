package com.KickOofEsports.KickOffEsports.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "clientes")
@Entity(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String cpf;
    private String dataNascimento;
    private String genero;
    private String idEnderecoCobranca;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Fetch(value = FetchMode.JOIN)
    @Setter(value = AccessLevel.NONE)
    public List<Enderecos> enderecosList = new ArrayList<>();

    @OneToMany
    private List<Pedido> produtos = new ArrayList<>();


    public Cliente(String nomeCompleto, String email, String senha, String cpf, String dataNascimento, String genero) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }
}
