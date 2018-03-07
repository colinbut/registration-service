FROM frolvlad/alpine-oraclejdk8:slim

# this is where Spring Boot Application creates working directories for tomcat by default
# Optional here but necessary if needed to write to filesystem
VOLUME /tmp

ADD registration-service-1.0-SNAPSHOT.jar app.jar

# so the jar has modified time
# optional here - but must be necessary for static content like html
RUN sh -c 'touch /app.jar'

# reduce tomcat startup time - source of entropy
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
