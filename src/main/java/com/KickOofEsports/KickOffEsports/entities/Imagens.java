package com.KickOofEsports.KickOffEsports.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Imagens {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   private String url;

   @ManyToOne
   @JoinColumn(name = "produto_id", nullable = false)
   private Produto produto;

   public Imagens(Produto produto, String url) {
      this.url = url;
      this.produto = produto;
   }
}
