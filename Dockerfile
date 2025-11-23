# Etapa de build com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia o pom.xml e baixa dependências antes (melhor cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o restante do projeto
COPY src ./src

# Corrige o encoding do application.properties e mantém as variáveis
RUN apt-get update && apt-get install -y file && \
    file -i src/main/resources/application.properties

# Build com valores placeholder para as variáveis (IMPORTANTE!)
RUN mvn clean package -DskipTests \
    -Ddb.user=placeholder_user \
    -Ddb.password=placeholder_password \
    -DGOOGLE_API_KEY=placeholder_key

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