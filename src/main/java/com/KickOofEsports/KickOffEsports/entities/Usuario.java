package com.KickOofEsports.KickOffEsports.entities;

import com.KickOofEsports.KickOffEsports.entities.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Table(name = "usuarios")
@Entity(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private UserRole role;
    private Boolean status;

    public Usuario(String nome, String email, String cpf, String senha, String role) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.role = UserRole.valueOf(role.toUpperCase());
        this.status = true;

    }

}
