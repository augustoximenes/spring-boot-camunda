# spring-boot-camunda-bpm
Sample project which illustrates how to setup a Spring Boot project whit Camunda BPM.

## Create the project
https://start.camunda.com - the default project was created in the src directory.

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
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.workflow.Application"] 
```

## Build image
```
$ docker build . -t spring-boot-camunda-bpm/my-project
```

## Run image
```
$ docker run -p 8080:8080 spring-boot-camunda-bpm/my-project
```

## Openshift
If you want to use a private image registry, an Image Pull Secret must be created similar to the image below:

![Openshift](docs/images/Openshift%20-%20Image%20Pull%20Secret.png)

For github, the password field is a personal access tokens!