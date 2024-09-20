# Usa una imagen base de Java
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR generado por Spring Boot al contenedor
COPY target/mi-aplicacion.jar /app/mi-aplicacion.jar

# Expone el puerto 8080 (o el puerto que use tu aplicación)
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/mi-aplicacion.jar"]
