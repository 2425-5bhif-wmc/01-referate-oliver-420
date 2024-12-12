FROM eclipse-temurin:21-jre

RUN mkdir -p /opt/application
COPY *-runner.jar /opt/application/dbapp.jar
COPY import.sql /import.sql
WORKDIR /opt/application
CMD [ "java", "-jar", "dbapp.jar" ]