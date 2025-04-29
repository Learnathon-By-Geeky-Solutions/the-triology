# ── STAGE 1: build the fat JAR with Maven & Java 21 ─────────────────
FROM maven:3.10.1-eclipse-temurin-21 AS builder
WORKDIR /app

# copy only pom.xml to leverage layer‐caching for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# copy source & build
COPY src ./src
RUN mvn clean package -DskipTests -B

# ── STAGE 2: runtime image with Corretto 21 ────────────────────────
FROM amazoncorretto:21-alpine
WORKDIR /app

# copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# tell Docker (and Render) which port your Spring Boot app listens on
EXPOSE 8080

# at runtime, Render will inject your env-vars:
#   DB_HOST, DB_PORT, DB_DATABASE, DB_USER, DB_PASSWORD,
#   JWT_SECRET_KEY, JWT_EXPIRATION_TIME, SPRING_PROFILES_ACTIVE, etc.
ENTRYPOINT ["java", "-jar", "app.jar"]
