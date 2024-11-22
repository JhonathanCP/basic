# Usar la imagen oficial de OpenJDK 17 como base
FROM eclipse-temurin:17-jdk-jammy

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado de la aplicación al contenedor
COPY target/mediapp-backend-0.0.1-SNAPSHOT.jar /app/mediapp-backend-0.0.1-SNAPSHOT.jar

# Exponer el puerto en el que corre la aplicación (por defecto en Spring Boot es 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app/mediapp-backend-0.0.1-SNAPSHOT.jar"]
