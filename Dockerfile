FROM openjdk:8-jdk
WORKDIR /app
COPY target/unbhelp-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

ENV DB_HOST=db
ENV DB_PORT=5432
ENV DB_NAME=unbavaliacoes
ENV DB_USER=postgres
ENV DB_PASSWORD=123456

CMD ["java", "-jar", "unbhelp-0.0.1-SNAPSHOT.jar"]