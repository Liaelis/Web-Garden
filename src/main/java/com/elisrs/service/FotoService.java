package com.elisrs.service;

import com.elisrs.enty.Foto;
import com.elisrs.enty.Planta;
import com.elisrs.repository.FotoRepository;
import com.elisrs.repository.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Service
public class FotoService {

    @Autowired
    FotoRepository fotoRepository;
    @Autowired
    PlantaRepository plantaRepository;

    public boolean salvarFoto(Integer id, MultipartFile multipartFile) throws IOException {
       Foto foto = new Foto();
       foto.setImagemplanta(multipartFile.getBytes());
       Foto imagemSalva = fotoRepository.save(foto);
        Optional<Planta> optionalPlanta = plantaRepository.findById(id);
        Planta planta = optionalPlanta.get();
        planta.setFoto(imagemSalva);
        plantaRepository.save(planta);
        return true;

    }
}
