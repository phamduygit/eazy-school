FROM eclipse-temurin:17

LABEL mentainer="javaguides.net@gmail.com"

WORKDIR /app

COPY learningspringboot-0.0.1-SNAPSHOT.jar /app/learningspringboot-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "learningspringboot-0.0.1-SNAPSHOT.jar"]