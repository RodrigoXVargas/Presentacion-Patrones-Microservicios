FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/shopping-service-0.0.1.jar
COPY ${JAR_FILE} shopping_app.jar
ENTRYPOINT ["java", "-jar", "shopping_app.jar"]