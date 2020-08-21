package com.elisrs.service;

import com.elisrs.enty.RecuperacaoSenha;
import com.elisrs.enty.Usuario;
import com.elisrs.repository.RecuperaSenhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecuperaSenhaService {

    @Autowired
    RecuperaSenhaRepository recuperaSenhaRepository;

    public RecuperacaoSenha criaRecuperacao(Usuario usuario) {
        String codigoTeste;
        do {
            codigoTeste = geraCodigo();

        } while (recuperaSenhaRepository.findByCodigo(codigoTeste).isPresent());

        RecuperacaoSenha recuperacaoSenha = new RecuperacaoSenha();
        recuperacaoSenha.setUsuario(usuario);
        recuperacaoSenha.setCodigo(codigoTeste);
        recuperacaoSenha.setExpiracao(LocalDateTime.now());
        recuperaSenhaRepository.save(recuperacaoSenha);
        RecuperacaoSenha recuperaTeste = buscarRecuperação(codigoTeste);
        if (recuperaTeste != null) {
            return recuperaTeste;
        } else {
            return null;
        }
    }

    public String geraCodigo() {
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        System.out.println(myRandom.substring(0, 20));
        return myRandom;
    }


    public RecuperacaoSenha buscarRecuperação(String codigo) {
        Optional<RecuperacaoSenha> opRec = recuperaSenhaRepository.findByCodigo(codigo);
        if (opRec.isPresent()) {
            RecuperacaoSenha recuperacaoSenha = opRec.get();
            return recuperacaoSenha;
        } else {
            System.out.printf("não encontrado");
            return null;
        }
    }
}
