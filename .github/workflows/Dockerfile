# ── STAGE 1: build the fat JAR with Maven & Java 21 ───────────────
FROM maven:3.10.1-openjdk-21 AS builder
WORKDIR /app

# cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# build the project
COPY src ./src
RUN mvn clean package -DskipTests -B

# ── STAGE 2: runtime image with Corretto 21 ───────────────────────
FROM amazoncorretto:21-alpine
WORKDIR /app

# copy the built JAR
COPY --from=builder /app/target/*.jar app.jar

# expose your Spring Boot port
EXPOSE 8080

# run it (Render will inject your env-vars: DB_HOST, DB_PORT, etc.)
ENTRYPOINT ["java", "-jar", "app.jar"]
