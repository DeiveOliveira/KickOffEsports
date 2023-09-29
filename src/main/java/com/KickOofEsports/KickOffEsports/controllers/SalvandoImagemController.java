//package com.KickOofEsports.KickOffEsports.controllers;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Controller
//public class SalvandoImagemController {
//
//    private static String diretorio = "src/main/resources/static/img/imagensDosProdutos";
//
//    @PostMapping("/salvarImg")
//    public ResponseEntity<?> upload(MultipartFile file){
//        if (file == null) {
//            throw new IllegalArgumentException("Arquivo não pode ser nulo");
//        }
//        try{
//            String nomeImagem = file.getOriginalFilename().replace(" ", "");
//            Path path = Paths.get(diretorio, nomeImagem);
//
//            // Verifica se o diretório existe. Se não existir, cria ele.
//            if (!Files.exists(path.getParent())) {
//                Files.createDirectories(path.getParent());
//            }
//
//            Files.write(path, file.getBytes());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return ResponseEntity.ok().body(null);
//    }
//}
