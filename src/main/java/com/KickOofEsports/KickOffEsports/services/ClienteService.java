package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Cliente;

import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.services.exceptions.CpfDiferentesExceptions;
import com.KickOofEsports.KickOffEsports.services.exceptions.EmailDiferentesException;
import com.KickOofEsports.KickOffEsports.services.exceptions.RecursoNaoEncontradoException;
import com.KickOofEsports.KickOffEsports.services.exceptions.SenhaDiferenteException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    ClienteRepository repository;

    public Cliente cadastrar(Cliente cliente){
        String criptografando = this.passwordEncoder.encode(cliente.getSenha());
        cliente.setSenha(criptografando);
        Cliente cli = new Cliente(cliente.getNomeCompleto(), cliente.getEmail(), cliente.getSenha(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getGenero());
        return repository.save(cli);
    }

    public Optional<?> procurarPorId(String id){
        return Optional.of(repository.getReferenceById(id));
    }

    public Cliente login(String email, String senha){
        Cliente cliente = repository.findByEmail(email);
        if(cliente != null && validarSenha(cliente, senha)){
            return cliente;
        }
        throw new SenhaDiferenteException();
    }

    public boolean validarSenha(Cliente cliente, String senha){
        return passwordEncoder.matches(senha, cliente.getSenha());
    }

    public Cliente atualizar(String id, Cliente clienteAtualizado){
        try{
            Cliente clienteDesatualizado = repository.getReferenceById(id);
            if(!clienteAtualizado.getEmail().equals(clienteDesatualizado.getEmail())){
                throw new EmailDiferentesException();
            }
            if(!clienteAtualizado.getCpf().equals(clienteDesatualizado.getCpf())){
                throw new CpfDiferentesExceptions();
            }
            atualizarData(clienteDesatualizado, clienteAtualizado);
            return repository.save(clienteDesatualizado);
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontradoException(id);
        }
    }

    public void atualizarData(Cliente clienteDesatualizado, Cliente clienteAtualizado) {
        if (clienteAtualizado.getSenha() != null) {
            clienteDesatualizado.setSenha(passwordEncoder.encode(clienteAtualizado.getSenha()));
        }
        clienteDesatualizado.setNomeCompleto(clienteAtualizado.getNomeCompleto());
        clienteDesatualizado.setEmail(clienteAtualizado.getEmail());
        clienteDesatualizado.setCpf(clienteAtualizado.getCpf());
    }
}
