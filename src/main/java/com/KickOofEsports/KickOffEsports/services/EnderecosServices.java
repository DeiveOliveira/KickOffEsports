package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Enderecos;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.repositories.EnderecosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecosServices {

    @Autowired
    EnderecosRepository enderecosRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public List<Enderecos> cadastrar(List<Enderecos> enderecos, String idUsuario) {
        Cliente cliente = clienteRepository.getReferenceById(idUsuario);
        List<Enderecos> novosEnderecos = new ArrayList<>();

        for (Enderecos endereco : enderecos) {
            Enderecos novoEndereco = new Enderecos(endereco.getCep(), endereco.getLogradouro(), endereco.getNumero(),
                    endereco.getComplemento(), endereco.getBairro(), endereco.getCidade(), endereco.getUf(), cliente);
            cliente.getEnderecosList().add(novoEndereco);
            novosEnderecos.add(novoEndereco);
        }

        clienteRepository.save(cliente);
        return enderecosRepository.saveAll(novosEnderecos);
    }

}
