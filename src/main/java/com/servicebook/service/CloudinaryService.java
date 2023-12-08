package com.servicebook.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {

  private Cloudinary cloudinary;

  private Map<String, String> valores = new HashMap<>();

//    public CloudinaryService(){
//        valores.put("cloud_name", "dsvcbfopx");
//        valores.put("api_key", "884734765111849");
//        valores.put("api_secret", "ny-wN-6QQkV8gsroFpvAkZZEaJU");
//        cloudinary = new Cloudinary(valores);
//    }
// nueva configuracion Cloudinary 
  public CloudinaryService() {
    valores.put("cloud_name", "dte154gnk");
    valores.put("api_key", "146417589721878");
    valores.put("api_secret", "EkVGVIZIYgosXmm8QHFbR2x_jgE");
    cloudinary = new Cloudinary(valores);
  }

  public Map subirFoto(MultipartFile multipartFile) throws IOException {
    File archivo = convetir(multipartFile);
    Map resultado = cloudinary.uploader().upload(archivo, ObjectUtils.emptyMap());
    archivo.delete();   
    return resultado;
  }

  public Map borrar(String id) throws IOException {
    return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
  }

  public File convetir(MultipartFile multipartFile) throws IOException {
    File archivo = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    FileOutputStream feo = new FileOutputStream(archivo);
    feo.write(multipartFile.getBytes());
    feo.close();
    return archivo;
  }
}
