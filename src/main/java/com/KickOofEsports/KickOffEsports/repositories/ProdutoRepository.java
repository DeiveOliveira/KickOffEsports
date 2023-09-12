package com.KickOofEsports.KickOffEsports.repositories;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
}
