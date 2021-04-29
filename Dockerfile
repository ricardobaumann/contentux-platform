FROM openjdk:11 AS build
COPY . /app
WORKDIR /app
RUN ./gradlew clean build

FROM openjdk:11-jre-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /app/build/libs/*.jar /app/contentux-platform.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=default,prod","-jar","/app/contentux-platform.jar"]