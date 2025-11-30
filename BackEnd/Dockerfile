FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/BackEnd-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 10000
ENV PORT=10000
ENTRYPOINT ["java", "-jar", "app.jar"]