# Use uma imagem base com OpenJDK
FROM openjdk:17-jdk-alpine

# Adicione o JAR gerado para o container
COPY target/Projetoweb-0.0.1-SNAPSHOT.jar /app/tarefas.jar

# Defina o diretório de trabalho
WORKDIR /app

# Comando para rodar o aplicativo Spring Boot
ENTRYPOINT ["java", "-jar", "tarefas.jar"]

# Exponha a porta padrão da aplicação
EXPOSE 8080

