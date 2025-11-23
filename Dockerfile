# Etapa de build com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia tudo
COPY . .

# Build simples - YAML não tem os problemas de encoding do properties
RUN mvn clean package -DskipTests

# Etapa de execução com JDK 17
FROM eclipse-temurin:17-jre
WORKDIR /app

# Cria usuário sem privilégios
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser
USER appuser

# Copia o jar do builder
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]