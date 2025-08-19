package com.servicebook.service.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Servicio simplificado para demo sin Cloudinary real
 */
@Service
@Profile("prod")
public class SimpleCloudinaryService {

    public Map subirFoto(MultipartFile multipartFile) throws IOException {
        Map<String, String> result = new HashMap<>();
        result.put("original_filename", multipartFile.getOriginalFilename());
        result.put("url", "/img/usuario-de-perfil.png");
        result.put("public_id", UUID.randomUUID().toString());
        return result;
    }

    public Map borrar(String id) throws IOException {
        Map<String, String> result = new HashMap<>();
        result.put("result", "ok");
        return result;
    }
}
