FROM openjdk:11.0.11-jre
VOLUME /tmp

ENV DATABASE_HOST=postgres_container
ENV DATABASE_PORT=5432
ENV DATABASE_USERNAME=postgres
ENV DATABASE_PASSWORD=changeme

ADD target/demo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT exec java -jar app.jar