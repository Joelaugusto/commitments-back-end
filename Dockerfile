FROM maven:3.6.0-jdk-13-alpine as maven
RUN apk add --no-cache libstdc++

COPY . .
COPY .env.example .env
RUN mvn dependency:go-offline -B

COPY ./src ./api/src
RUN mvn clean install -DskipTests

FROM openjdk:16-alpine3.13
EXPOSE 8080
COPY --from=maven /api/target/authentication-api-1.0.jar ./
CMD ["java", "-jar", "./commitments-api-1.0.jar"]