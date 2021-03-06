FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR
ARG DATABASE_HOST
ARG DATABASE_USER
ARG DATABASE_PASSWORD
COPY ${JAR} app.jar
ENV DATABASE_HOST ${DATABASE_HOST}
ENV DATABASE_USER ${DATABASE_USER}
ENV DATABASE_PASSWORD ${DATABASE_PASSWORD}
ENTRYPOINT ["java","-jar", "app.jar"]