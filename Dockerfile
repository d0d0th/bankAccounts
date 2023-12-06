FROM openjdk:17-jdk-slim

WORKDIR /usr/app

COPY ./target /usr/app/

EXPOSE 8080

CMD [ "java", "-jar", "bank-accounts-0.0.1-SNAPSHOT.jar" ]