package com.KickOofEsports.KickOffEsports.services;

import com.KickOofEsports.KickOffEsports.entities.Usuario;
import com.KickOofEsports.KickOffEsports.repositories.UsuariosRepository;
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
public class EditarService {

    @Autowired
    UsuariosRepository repository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Optional<Usuario> procurarPorId(String id){
        return Optional.of(repository.getReferenceById(id));
    }

    public Usuario atualizar(String id, Usuario usuarioAtualizado){
        try{
            Usuario usuarioDesatualizado = repository.getReferenceById(id);
            if(!this.passwordEncoder.matches(usuarioAtualizado.getSenha(), usuarioDesatualizado.getSenha())){
                throw new SenhaDiferenteException();
            }
            if(!usuarioAtualizado.getEmail().equals(usuarioDesatualizado.getEmail())){
                throw new EmailDiferentesException();
            }
            if(!usuarioAtualizado.getCpf().equals(usuarioDesatualizado.getCpf())){
                throw new CpfDiferentesExceptions();
            }
            atualizarData(usuarioDesatualizado, usuarioAtualizado);
            return repository.save(usuarioDesatualizado);
        }catch (EntityNotFoundException e){
            throw new RecursoNaoEncontradoException(id);
        }
    }

    public void atualizarData(Usuario usuarioDesatualizado, Usuario usuarioAtualizado){
        usuarioDesatualizado.setNome(usuarioAtualizado.getNome());
        usuarioDesatualizado.setEmail(usuarioAtualizado.getEmail());
        usuarioDesatualizado.setCpf(usuarioAtualizado.getCpf());
        usuarioDesatualizado.setSenha(this.passwordEncoder.encode(usuarioAtualizado.getSenha()));
        usuarioDesatualizado.setRole(usuarioAtualizado.getRole());
    }
}
