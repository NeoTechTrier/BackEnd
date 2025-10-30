FROM openjdk:21

COPY ecommerce/target/ecommerce-0.0.1-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]

