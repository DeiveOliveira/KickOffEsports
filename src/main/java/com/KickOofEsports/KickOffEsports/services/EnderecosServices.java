package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Enderecos;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.repositories.EnderecosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecosServices {

    @Autowired
    EnderecosRepository enderecosRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public Enderecos cadastrar(Enderecos enderecos, String idUsuario){
        Cliente cliente = clienteRepository.getReferenceById(idUsuario);
        Enderecos enderecos1 = new Enderecos(enderecos.getCep(), enderecos.getLogradouro(), enderecos.getNumero(), enderecos.getComplemento(), enderecos.getBairro(), enderecos.getCidade(), enderecos.getUf(), cliente);
        cliente.enderecosList.add(enderecos1);
        clienteRepository.save(cliente);
        return enderecosRepository.save(enderecos1);
    }
}
