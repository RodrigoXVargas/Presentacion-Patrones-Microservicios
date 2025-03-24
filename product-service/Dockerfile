FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/product-service-0.0.1.jar
COPY ${JAR_FILE} product_app.jar
ENTRYPOINT ["java", "-jar", "product_app.jar"]