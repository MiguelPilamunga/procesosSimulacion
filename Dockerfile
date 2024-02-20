# Usa la imagen oficial de Maven para compilar
FROM maven:3.8-openjdk-17-slim AS builder

# Establece el directorio de trabajo en la carpeta del proyecto
WORKDIR /app

# Copia el archivo pom.xml para descargar las dependencias
COPY pom.xml .

# Descarga las dependencias
RUN mvn dependency:go-offline -B

# Copia el resto de los archivos del proyecto
COPY src ./src

# Compila la aplicación
RUN mvn package -DskipTests

# Usa la imagen oficial de OpenJDK 17 para ejecutar la aplicación
FROM openjdk:17-slim

# Establece el directorio de trabajo en la carpeta del proyecto
WORKDIR /app

# Copia el JAR compilado desde la etapa anterior
COPY --from=builder /app/target/tu-proyecto.jar ./app.jar

# Puerto en el que se ejecuta la aplicación (ajusta según las necesidades de tu aplicación)
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]
