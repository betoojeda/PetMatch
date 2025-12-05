# Etapa 1: Construir el artefacto .jar con Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# El ENTRYPOINT ahora puede usar JAVA_TOOL_OPTIONS para argumentos de JVM, incluyendo la depuración.
# No necesitamos cambiar esta línea, ya que JAVA_TOOL_OPTIONS se inyectará automáticamente.
ENTRYPOINT ["java", "-jar", "app.jar"]