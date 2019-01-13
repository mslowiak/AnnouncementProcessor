FROM openjdk:8-jdk-alpine

RUN apk update
RUN apk add maven

RUN mkdir -p /srv/announcement-processor/api
WORKDIR /srv/announcement-processor/api

ADD pom.xml /srv/announcement-processor/api
COPY src /srv/announcement-processor/api/src

RUN ["mvn", "clean", "install", "-DskipTests"]

EXPOSE 8080

CMD java -jar -Dspring.profiles.active=demo /srv/announcement-processor/api/target/api-service.jar