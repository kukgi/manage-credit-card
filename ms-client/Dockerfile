FROM gradle:3.4-jdk8 AS build
WORKDIR /app
COPY . .
RUN gradle build --exclude-task test

FROM openjdk:19
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app/app.jar"]