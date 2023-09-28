package com.KickOofEsports.KickOffEsports.config;

import com.KickOofEsports.KickOffEsports.entities.Imagens;
import com.KickOofEsports.KickOffEsports.entities.Produto;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.ListaDeImagensRepository;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import com.KickOofEsports.KickOffEsports.services.CadastrarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CadastrarService cadastrar;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ListaDeImagensRepository listaDeImagens;



    @Override
    public void run(String... args) throws Exception {

        Usuario u1 = new Usuario("Augusto", "augusto@gmail.com", "13577155019", "12345", "ESTOQUISTA");
        Usuario u2 = new Usuario("Brenno", "brenno@gmail.com", "53114093043", "12345", "ADMIN");
        Usuario u3 = new Usuario("Deive", "deive@gmail.com", "21439151016", "12345",  "ADMIN");

        cadastrar.cadastrar(u1);
        cadastrar.cadastrar(u2);
        cadastrar.cadastrar(u3);



        Produto p1 = new Produto("Camisa do Real Madrid", 5.00, "Camisa do Real Madrid de 2023", 350.00, 10);
        Produto p2 = new Produto("Camisa Retro do Barcelona", 3.50, "Camisa do Barcelona de 2016", 160.00, 30);
        Produto p3 = new Produto("Camisa da Alemanha", 1.00, "Camisa da Juventus do CR7", 400.00, 5);
        Produto p4 = new Produto("Camisa do Japão", 3.00, "Camisa do Japão, edição limitada", 400.00, 5);

        produtoRepository.saveAll(Arrays.asList(p1,p2,p3, p4));


        Imagens l1 = new Imagens(p1, "/img/imagensDosProdutos/RealMadrir.jpg");
        Imagens l2 = new Imagens(p1, "/img/imagensDosProdutos/RealMadrirCosta.jpg");
        Imagens l3 = new Imagens(p2, "/img/imagensDosProdutos/BarçaFrente.jpg");
        Imagens l4 = new Imagens(p2, "/img/imagensDosProdutos/BarçaCostas.jpg");
        Imagens l5 = new Imagens(p2, "/img/imagensDosProdutos/Barça.jpg");
        Imagens l6 = new Imagens(p3, "/img/imagensDosProdutos/AlemanhaFrente.jpg");
        Imagens l7 = new Imagens(p3, "/img/imagensDosProdutos/AlemanhaCostas.jpg");
        Imagens l8 = new Imagens(p4, "/img/imagensDosProdutos/JapaoFrente.jpg");
        Imagens l9 = new Imagens(p4, "/img/imagensDosProdutos/JapaoCostas.jpg");

        listaDeImagens.saveAll(Arrays.asList(l1, l2, l3, l4, l5, l6, l7, l8, l9));


    }
}
