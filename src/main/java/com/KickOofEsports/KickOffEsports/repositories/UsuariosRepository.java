package com.KickOofEsports.KickOffEsports.repositories;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuariosRepository extends JpaRepository<Usuario, String> {

    Usuario findByEmail(String email);

    Usuario findByCpf(String cpf);

    List<Usuario> findByNome(String nome);
}
