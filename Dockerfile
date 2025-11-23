# Etapa de build com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia o pom.xml e baixa dependências antes (melhor cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o restante do projeto
COPY src ./src

# Remove o application.properties problemático e cria um novo
RUN rm -f src/main/resources/application.properties && \
    echo "# Application Properties (Generated)" > src/main/resources/application.properties && \
    echo "spring.datasource.url=jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL" >> src/main/resources/application.properties && \
    echo "spring.datasource.driver-class-name=oracle.jdbc.OracleDriver" >> src/main/resources/application.properties && \
    echo "spring.jpa.show-sql=true" >> src/main/resources/application.properties && \
    echo "spring.jpa.hibernate.ddl-auto=update" >> src/main/resources/application.properties && \
    echo "spring.jpa.properties.hibernate.format_sql=true" >> src/main/resources/application.properties && \
    echo "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect" >> src/main/resources/application.properties && \
    echo "spring.thymeleaf.cache=false" >> src/main/resources/application.properties && \
    echo "spring.ai.google.ai.chat.options.model=gemini-flash-latest" >> src/main/resources/application.properties && \
    echo "server.port=8080" >> src/main/resources/application.properties && \
    echo "spring.cache.type=simple" >> src/main/resources/application.properties && \
    echo "spring.cache.cache-names=questionarioCache,chatCache" >> src/main/resources/application.properties && \
    echo "spring.cache.simple.time-to-live=1800000" >> src/main/resources/application.properties

# Build da aplicação
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