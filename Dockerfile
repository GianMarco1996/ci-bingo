FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./target/bingo-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "bingo-0.0.1-SNAPSHOT.jar"]