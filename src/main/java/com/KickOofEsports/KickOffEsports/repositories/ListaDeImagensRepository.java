package com.KickOofEsports.KickOffEsports.repositories;

import com.KickOofEsports.KickOffEsports.entities.Imagens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaDeImagensRepository extends JpaRepository<Imagens, String> {
}
