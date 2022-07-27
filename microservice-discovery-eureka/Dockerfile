FROM openjdk:8-jre-alpine
COPY target/*-SNAPSHOT.jar /app.jar
CMD ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-jar", "/app.jar"]