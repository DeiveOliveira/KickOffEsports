package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Cliente;
import com.KickOofEsports.KickOffEsports.repositories.ClienteRepository;
import com.KickOofEsports.KickOffEsports.repositories.UsuariosRepository;
import com.KickOofEsports.KickOffEsports.services.exceptions.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastrarClienteService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    ClienteRepository repository;

    public Cliente cadastrar(Cliente cliente){
        String criptografando = this.passwordEncoder.encode(cliente.getSenha());
        cliente.setSenha(criptografando);
        Cliente cli = new Cliente(cliente.getNomeCompleto(), cliente.getEmail(), cliente.getSenha(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getGenero());
        return repository.save(cli);
    }

    public Cliente findById(String id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new RecursoNaoEncontradoException(id));
    }
}
