package com.KickOofEsports.KickOffEsports.repositories;

import com.KickOofEsports.KickOffEsports.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

    List<Produto> findByNome(String nome);

    List<Produto> findByDescricaoContaining(String descricao);
}
