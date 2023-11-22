package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Enderecos;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.repositories.EnderecosRepository;
import com.KickOofEsports.KickOffEsports.services.exceptions.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class EnderecosServices {

    @Autowired
    EnderecosRepository enderecosRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Transactional
    public Enderecos cadastrar(Enderecos enderecos, String idUsuario){
        Cliente cliente = clienteRepository.getReferenceById(idUsuario);
        Enderecos enderecos1 = new Enderecos(enderecos.getCep(), enderecos.getLogradouro(), enderecos.getNumero(), enderecos.getComplemento(), enderecos.getBairro(), enderecos.getCidade(), enderecos.getUf(), cliente);
        if(cliente.enderecosList.isEmpty()){
            cliente.enderecosList.add(enderecos1);
            enderecosRepository.save(enderecos1);
            cliente.setIdEnderecoCobranca(enderecos1.getId());
            clienteRepository.save(cliente);
            return enderecos1;
        }
        cliente.enderecosList.add(enderecos1);
        clienteRepository.save(cliente);
        return enderecosRepository.save(enderecos1);
    }

    public Enderecos atualizar(String id, Enderecos enderecoAtualizado) {
        try {
            Enderecos enderecoDesatualizado = enderecosRepository.getReferenceById(id);

            if (enderecoAtualizado.isEnderecoPadrao()) {
                enderecoDesatualizado.getCliente().getEnderecosList().forEach(endereco -> endereco.setEnderecoPadrao(false));
            }

            atualizarData(enderecoDesatualizado, enderecoAtualizado);

            return enderecosRepository.save(enderecoDesatualizado);
        } catch (RecursoNaoEncontradoException e) {
            throw e;
        }
    }

    public void atualizarData(Enderecos enderecoDesatualizado, Enderecos enderecoAtualizado) {
        enderecoDesatualizado.setCep(enderecoAtualizado.getCep());
        enderecoDesatualizado.setLogradouro(enderecoAtualizado.getLogradouro());
        enderecoDesatualizado.setNumero(enderecoAtualizado.getNumero());
        enderecoDesatualizado.setComplemento(enderecoAtualizado.getComplemento());
        enderecoDesatualizado.setBairro(enderecoAtualizado.getBairro());
        enderecoDesatualizado.setCidade(enderecoAtualizado.getCidade());
        enderecoDesatualizado.setUf(enderecoAtualizado.getUf());
        enderecoDesatualizado.setEnderecoPadrao(enderecoAtualizado.isEnderecoPadrao());
    }

    public List<Enderecos> procurarTodosEnderecos(String id) {
        Cliente cliente = clienteRepository.getReferenceById(id);
        return cliente.getEnderecosList();
    }

    public Enderecos procurarEnderecoPorId(String id) {
        return enderecosRepository.getReferenceById(id);
    }
}