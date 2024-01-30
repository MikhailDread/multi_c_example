FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "application.jar"]