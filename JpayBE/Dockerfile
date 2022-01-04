# Build stage
FROM adoptopenjdk/maven-openjdk11 as build-stage
WORKDIR /app
COPY . .
RUN mvn clean install

# Production Stage
FROM adoptopenjdk/openjdk11
COPY --from=build-stage /app/target/*.jar jpayBE.jar
COPY --from=build-stage /app/sample.db sample.db
ENTRYPOINT ["java", "-jar", "jpayBE.jar"]
