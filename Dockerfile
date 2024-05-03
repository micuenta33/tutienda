# Usa una imagen base con Java y Maven instalados
FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY . .
RUN ./gradlew bootJar --no-daemon

FROM openjdk:21-jdk-slim
EXPOSE 8080
COPY --from=build /build/libs/Tutienda-0.0.1-SNAPSHOT.jar app.jar

# Comando para ejecutar tu aplicación cuando se inicie el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]
