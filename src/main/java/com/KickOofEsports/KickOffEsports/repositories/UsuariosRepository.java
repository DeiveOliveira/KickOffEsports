package com.KickOofEsports.KickOffEsports.repositories;

import com.KickOofEsports.KickOffEsports.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario, String> {
}
