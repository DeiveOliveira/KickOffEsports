package com.KickOofEsports.KickOffEsports.repositories;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario, String> {

    Usuario findByEmail(String email);
}
