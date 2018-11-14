FROM openjdk:8-jdk-alpine

RUN apk update
RUN apk add maven

RUN mkdir -p /srv/announcement-processor/extractor
WORKDIR /srv/announcement-processor/extractor

ADD pom.xml /srv/announcement-processor/extractor
COPY src /srv/announcement-processor/extractor/src

RUN ["mvn", "clean", "install", "-DskipTests"]

CMD java -jar /srv/announcement-processor/extractor/target/extractor-service.jar