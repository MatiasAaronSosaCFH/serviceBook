#!/bin/bash

echo "🚀 Preparando ServiceBook para deploy..."

# 1. Crear application-prod.properties
echo "📝 Creando application-prod.properties..."
cat > src/main/resources/application-prod.properties << 'EOF'
# Configuración para producción
server.port=${PORT:8080}

# Base de datos
spring.datasource.url=${DATABASE_URL:jdbc:mysql://localhost:3306/servicebook}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=false

# Seguridad
server.error.include-message=never
server.error.include-binding-errors=never

# Multipart
spring.servlet.multipart.max-file-size=5MB
spring.servlet.multipart.max-request-size=5MB

# Logging
logging.level.com.servicebook=INFO
EOF

# 2. Actualizar application.properties principal
echo "🔧 Actualizando application.properties..."
cat > src/main/resources/application.properties << 'EOF'
# Perfil activo
spring.profiles.active=${SPRING_PROFILES_ACTIVE:dev}

# Configuración de desarrollo (por defecto)
spring.datasource.url=jdbc:mysql://localhost:3306/servicebook?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true

server.port=8080
EOF

# 3. Crear CloudinaryService simplificado para demo
echo "🖼️ Simplificando CloudinaryService..."
mkdir -p src/main/java/com/servicebook/service/demo

cat > src/main/java/com/servicebook/service/demo/SimpleCloudinaryService.java << 'EOF'
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
EOF

# 4. Crear README actualizado
echo "📚 Creando README.md..."
cat > README.md << 'EOF'
# 🏠 ServiceBook

Plataforma web para contratación de servicios domésticos desarrollada con Spring Boot.

## 🚀 Demo en vivo
**URL**: [En proceso de deploy]

## 🛠️ Tecnologías
- **Backend**: Spring Boot 2.7, Spring Security, JPA/Hibernate
- **Frontend**: Bootstrap 5, Thymeleaf, JavaScript
- **Base de datos**: MySQL
- **Deploy**: Railway

## 📋 Características
- ✅ Sistema de autenticación y autorización
- ✅ Gestión de usuarios (Cliente, Proveedor, Admin)
- ✅ Catálogo de servicios con fotos
- ✅ Sistema de contratación de trabajos
- ✅ Panel de administración
- ✅ Responsive design

## 🏃‍♂️ Ejecutar localmente

1. **Clonar repositorio**
```bash
git clone https://github.com/tu-usuario/servicebook.git
cd servicebook
```

2. **Configurar base de datos MySQL**
```sql
CREATE DATABASE servicebook;
```

3. **Ejecutar aplicación**
```bash
mvn spring-boot:run
```

4. **Acceder**: http://localhost:8080

## 👤 Usuarios de prueba
- **Admin**: admin@servicebook.com / admin123
- **Cliente**: cliente@test.com / cliente123
- **Proveedor**: proveedor@test.com / proveedor123

## 📧 Contacto
**Desarrollador**: Tu Nombre  
**Email**: tu.email@ejemplo.com  
**LinkedIn**: [Tu perfil]
EOF

echo "✅ Proyecto preparado para deploy!"
echo ""
echo "📋 Próximos pasos:"
echo "1. git add ."
echo "2. git commit -m 'Preparar para deploy en Railway'"
echo "3. git push origin main"
echo "4. Ir a railway.app y deployar desde GitHub"
echo ""
echo "🎉 ¡Tu proyecto estará listo para incluir en tu CV!"