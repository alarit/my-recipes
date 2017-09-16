FROM java:8-jre
MAINTAINER Alessandro Arici <alessandro.arici.dev@gmail.com>
VOLUME /tmp

ADD ./target/my-recipes-backend-1.0.0.jar app.jar
RUN sh -c 'touch /app.jar'

ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://myrecipes-mongo/recipes","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

EXPOSE 6000