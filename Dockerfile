FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/spring-security-jpa.jar spring-security-jpa.jar
ENTRYPOINT ["java","-jar","spring-security-jpa.jar"]