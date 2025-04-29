# ── STAGE 1: build the fat JAR with Maven & Java 21 ────────────────────
FROM maven:3.9.8-eclipse-temurin-21 AS builder
WORKDIR /app

# cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# compile & package
COPY src ./src
RUN mvn clean package -DskipTests -B

# ── STAGE 2: minimal runtime on Corretto 21 ───────────────────────────
FROM amazoncorretto:21-alpine
WORKDIR /app

# pull in your built JAR
COPY --from=builder /app/target/*.jar app.jar

# expose the port Spring Boot listens on
EXPOSE 8080

# at runtime, Render will inject your env-vars:
#   DB_DATABASE, DB_USER, DB_PASSWORD,
#   JWT_SECRET_KEY, JWT_EXPIRATION_TIME, SPRING_PROFILES_ACTIVE, etc.
ENTRYPOINT ["java","-jar","app.jar"]
