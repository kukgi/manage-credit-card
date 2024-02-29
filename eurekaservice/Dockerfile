FROM gradle:3.4-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN gradle clean build

FROM openjdk:19
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/app/app.jar"]