package com.KickOofEsports.KickOffEsports.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class UploadImagens {

    public static boolean fazerUploadImagens(MultipartFile file) {

            boolean sucessoUpload = false;
        if (file != null && !file.isEmpty()) {

            String nomeArquivo = file.getOriginalFilename();

            try {
                //Criando diretorio para armazenar imagens do produto

                String pastaUploadImagens = "src/main/resources/static/img/imagensProdutos";
                File dir = new File(pastaUploadImagens);

                if (!dir.exists()) {
                    dir.mkdir();
                }

                //Criando arquivo no diretorio
                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(file.getBytes());
                stream.close();

                System.out.println("Feito o upload");
                sucessoUpload = true;

            } catch (Exception e) {
                System.out.println("!!!!!!!!!!!!!!!!!!ERRO ao carregar imagem!!!!!!!!!!!!!!!!!!!!!!");

            }
        } else {
            System.out.println("Não há arquivos para ser carregar");
        }

        return sucessoUpload;
    }
}
