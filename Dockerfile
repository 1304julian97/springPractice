FROM openjdk:11.0.14.1-jre


ENV spring.profiles.active=test
COPY target/demo-0.0.1-SNAPSHOT.jar /demo.jar
CMD ["java", "-jar", "/demo.jar"]

