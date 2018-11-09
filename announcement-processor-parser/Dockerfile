FROM openjdk:8-jdk-alpine

RUN apk update
RUN apk add maven

RUN mkdir -p /srv/announcement-processor/parser
WORKDIR /srv/announcement-processor/parser

ADD pom.xml /srv/announcement-processor/parser
COPY src /srv/announcement-processor/parser/src

RUN ["mvn", "clean", "install", "-DskipTests"]

CMD java -jar /srv/announcement-processor/parser/target/parser-service.jar