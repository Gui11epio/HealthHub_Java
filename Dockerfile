# Etapa de build com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia o pom.xml e baixa dependências antes (melhor cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o restante do projeto
COPY src ./src

# Build SEM filtering - usa valores placeholder
RUN mvn clean package -DskipTests -Dmaven.resources.filtering=false

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