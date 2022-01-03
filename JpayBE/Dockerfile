# Develop stage
FROM adoptopenjdk/maven-openjdk11 as develop-stage
WORKDIR /app
COPY . .
RUN mvn clean install

# Build stage
FROM develop-stage as build-stage
RUN mvn package

# Production Stage
FROM adoptopenjdk/openjdk11
COPY --from=build-stage /app/target/*.jar jpayBE.jar
COPY sample.db sample.db
ENTRYPOINT ["java", "-jar", "jpayBE.jar"]
