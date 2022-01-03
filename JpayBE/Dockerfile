
FROM adoptopenjdk/openjdk11

ADD target/*.jar jpayBE.jar
ADD sample.db sample.db

ENTRYPOINT ["java", "-jar", "jpayBE.jar"]
