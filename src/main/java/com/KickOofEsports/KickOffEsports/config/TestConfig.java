package com.KickOofEsports.KickOffEsports.config;

import com.KickOofEsports.KickOffEsports.entities.*;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.repositories.ListaDeImagensRepository;
import com.KickOofEsports.KickOffEsports.repositories.PedidosRepository;
import com.KickOofEsports.KickOffEsports.repositories.ProdutoRepository;
import com.KickOofEsports.KickOffEsports.services.ClienteService;
import com.KickOofEsports.KickOffEsports.services.CadastrarService;
import com.KickOofEsports.KickOffEsports.services.EnderecosServices;
import com.KickOofEsports.KickOffEsports.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CadastrarService cadastrar;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ListaDeImagensRepository listaDeImagens;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecosServices enderecosServices;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoService pedidoService;

    @Override
    public void run(String... args) throws Exception {

        Usuario u1 = new Usuario("Augusto", "augusto@gmail.com", "13577155019", "12345", "ESTOQUISTA");
        Usuario u2 = new Usuario("Brenno", "brenno@gmail.com", "53114093043", "12345", "ADMIN");
        Usuario u3 = new Usuario("Deive", "deive@gmail.com", "21439151016", "12345",  "ADMIN");

        cadastrar.cadastrar(u1);
        cadastrar.cadastrar(u2);
        cadastrar.cadastrar(u3);

        Produto p1 = new Produto("Camisa do Real Madrid", 5.00, "Camisa do Real Madrid de 2023", 350.00, 1);
        Produto p2 = new Produto("Camisa Retro do Barcelona", 3.50, "Camisa do Barcelona de 2016", 160.00, 1);
        Produto p3 = new Produto("Camisa da Alemanha", 1.00, "Camisa da Juventus do CR7", 400.00, 1);
        Produto p4 = new Produto("Camisa do Japão", 3.00, "Camisa do Japão, edição limitada", 400.00, 1);

        produtoRepository.saveAll(Arrays.asList(p1,p2,p3, p4));

        Imagens l1 = new Imagens(p1, "RealMadrir.jpg");
        Imagens l2 = new Imagens(p1, "RealMadrirCosta.jpg");
        Imagens l3 = new Imagens(p2, "BarçaFrente.jpg");
        Imagens l4 = new Imagens(p2, "BarçaCostas.jpg");
        Imagens l5 = new Imagens(p2, "Barça.jpg");
        Imagens l6 = new Imagens(p3, "AlemanhaFrente.jpg");
        Imagens l7 = new Imagens(p3, "AlemanhaCostas.jpg");
        Imagens l8 = new Imagens(p4, "JapaoFrente.jpg");
        Imagens l9 = new Imagens(p4, "JapaoCostas.jpg");

        listaDeImagens.saveAll(Arrays.asList(l1, l2, l3, l4, l5, l6, l7, l8, l9));

        Cliente c1 = new Cliente("AugustoCliente", "augustoCliente@gmail.com", "12345", "66803699080", "2002-08-21\t", "Masculino");
        Cliente c2 = new Cliente("BrennoCliente", "brennoCliente@gmail.com", "12345", "19256439050", "2002-08-20\t", "Masculino");
        Cliente c3 = new Cliente("DeiveCliente", "deiveCliente@gmail.com", "12345", "98301097043", "2002-04-14\t", "Masculino");

        c1 = clienteService.cadastrar(c1);
        c2 = clienteService.cadastrar(c2);
        c3 = clienteService.cadastrar(c3);

        Enderecos e1 = new Enderecos("04845-150", "Rua jorge mendes", "60", " ", "Capao redondo", "São Paulo1", "SP1");
        Enderecos e2 = new Enderecos("04845-150", "Rua jorge mendes", "65", " ", "Capao redondo", "São Paulo2", "SP2");
        Enderecos e3 = new Enderecos("04845-150", "Rua jorge mendess", "62", " ", "Capao redondo", "São Paulo3", "SP3");

        e1 = enderecosServices.cadastrar(e1, c1.getId());
        e2 = enderecosServices.cadastrar(e2, c2.getId());
        e3 = enderecosServices.cadastrar(e3, c3.getId());

        List<Produto> pC1 = new ArrayList<>();
        List<Produto> pC2 = new ArrayList<>();
        List<Produto> pC3 = new ArrayList<>();


        pC1.add(p1);
        pC1.add(p2);

        pC2.add(p1);
//
//        pC3.add(p3);
//        pC3.add(p4);

        pedidoService.cadastrarPedidos(20.0, 5, pC1, c1.getId());
        pedidoService.cadastrarPedidos(20.0, 3, pC2, c2.getId());
//        pedidoService.cadastrarPedidos(20.0, 10, pC2, c3.getId());




    }
}
