# spring-boot-camunda-bpm
Sample project which illustrates how to setup a Spring Boot project whit Camunda BPM.

## Create the project
https://start.camunda.com

## Create Dockerfile
```
FROM openjdk:8-jdk-alpine AS builder
WORKDIR target/dependency
ARG APPJAR=target/*.jar
COPY ${APPJAR} app.jar
RUN jar -xf ./app.jar

FROM openjdk:8-jre-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","br.com.financeira.Application"] 
```

## Build imagem
```
$ docker build . -t camunda/my-project
```

## Run imagem
```
$ docker run -p 8080:8080 camunda/my-project
```