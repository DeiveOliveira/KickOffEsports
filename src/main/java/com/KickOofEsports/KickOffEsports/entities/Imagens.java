package com.KickOofEsports.KickOffEsports.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Imagens {

   @Id
   @GeneratedValue(strategy = GenerationType.UUID)

   private UUID id;
   private String url;

   @JsonBackReference
   @ManyToOne
   @JoinColumn(name = "produto_id", nullable = false)
   private Produto produto;

   public Imagens(Produto produto, String url) {
      this.url = url;
      this.produto = produto;
   }
}
