# build stage
FROM maven:3.8.6-jdk-11 as build-sage

WORKDIR /app

COPY ./ ./

RUN mvn clean package -Dmaven.test.skip
