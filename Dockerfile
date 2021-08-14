FROM openjdk:11
COPY target/forum-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
CMD java -jar forum-0.0.1-SNAPSHOT.jar