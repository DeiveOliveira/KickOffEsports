package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.entities.Enderecos;
import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.repositories.EnderecosRepository;
import com.KickOofEsports.KickOffEsports.services.exceptions.CpfDiferentesExceptions;
import com.KickOofEsports.KickOffEsports.services.exceptions.EmailDiferentesException;
import com.KickOofEsports.KickOffEsports.services.exceptions.RecursoNaoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    public Enderecos atualizar(String id, Enderecos enderecoAtualizado){
        try{
            Enderecos enderecoDesatualizado = enderecosRepository.getReferenceById(id);
            atualizarData(enderecoDesatualizado, enderecoAtualizado);
            return enderecosRepository.save(enderecoDesatualizado);
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontradoException(id);
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
        }
}