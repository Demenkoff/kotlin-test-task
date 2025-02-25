FROM openjdk:21-jdk

ENV ARTIFACT_NAME=kotlin-warm-0.0.1-SNAPSHOT.jar

COPY /build/libs/$ARTIFACT_NAME .

EXPOSE 8080

ENTRYPOINT exec java -jar ${ARTIFACT_NAME}