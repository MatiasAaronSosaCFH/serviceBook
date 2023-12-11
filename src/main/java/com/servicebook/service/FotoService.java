package com.servicebook.service;

//import com.cloudinary.Cloudinary;
//import com.cloudinary.Transformation;
//import com.cloudinary.utils.ObjectUtils;
import com.servicebook.models.Foto;
import com.servicebook.models.Trabajo;
import com.servicebook.models.dtos.FotoDtoRecibido;
import com.servicebook.repository.FotoRepository;
import com.servicebook.repository.TrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.util.ObjectUtils;

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;

    @Autowired
    private TrabajoRepository trabajoRepository;
//    @Autowired
//    private Cloudinary cloudinary;


//    public String subirFoto(MultipartFile archivo, String fotoId) throws IOException {
//        byte[] bytes = archivo.getBytes();
//
//        Map<String, Object> options = ObjectUtils.asMap(
//                "folder", folder,
//                "fotoId", fotoId
//        );
//        
//         Map uploadResult = cloudinary.uploader().upload(bytes, options);
//         
//         return (String)uploadResult.get("url");
//    }
    
    public Foto convertirDtoRecibido(FotoDtoRecibido foto){
        Foto fotoFinal = new Foto();
        fotoFinal.setFotoId(foto.fotoId());
        fotoFinal.setAlta(true);
        fotoFinal.setUrl(foto.url());
        fotoFinal.setNombre(foto.nombre());
        return fotoFinal;
    }

    public List<Foto> convertirDtoRecibido(List<FotoDtoRecibido> foto){
        List<Foto> fotosFinales = foto.stream().map(Foto::new).toList();
        Trabajo trabajo = trabajoRepository.buscarPorId(foto.get(0).trabajo()).orElse(null);
        //fotosFinales.forEach(foto1 -> foto1.setTrabajo(trabajo));

        return fotosFinales;
    }
}
