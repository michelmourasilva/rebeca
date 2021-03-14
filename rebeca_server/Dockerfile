#### Estágio 1 - Faz o build da aplicação
FROM openjdk:8-jdk-alpine as build

# Define o diretório de trabalho atual dentro da imagem
WORKDIR /app

# Copia o executável do maven para a imagem
COPY mvnw .
COPY .mvn .mvn

# Copie o arquivo pom.xml
COPY pom.xml .


# Cria todas as dependências em preparação para ficar offline.
# Esta é uma etapa separada, então as dependências serão armazenadas em cache, a menos que
# o arquivo pom.xml seja alterado.
RUN ./mvnw dependency:go-offline -B

# Copie a fonte do projeto
COPY src src

# Empacota a aplicação
RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

#### Estágio 2: uma imagem mínima do docker com comando para executar o aplicativo
FROM openjdk:8-jre-alpine

ARG DEPENDENCY=/app/target/dependency

# Copia as dependências do projeto do estágio de construção
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","br.rebeca.RebecaApplication"]