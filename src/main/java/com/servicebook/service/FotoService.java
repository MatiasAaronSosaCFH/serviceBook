package com.servicebook.service;

//import com.cloudinary.Cloudinary;
//import com.cloudinary.Transformation;
//import com.cloudinary.utils.ObjectUtils;
import com.servicebook.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;
import org.springframework.util.ObjectUtils;

@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;

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
    
    
}
