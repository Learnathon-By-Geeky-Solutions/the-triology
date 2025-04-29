# ── STAGE 1: build the fat JAR with Maven & Java 21 ────────────────────
FROM maven:3.9.8-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

# ── STAGE 2: minimal runtime on Corretto 21 ──────────────────────────
FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080

# default to prod profile (override in Render)
ENV SPRING_PROFILES_ACTIVE=prod

# optional healthcheck
HEALTHCHECK --interval=30s --timeout=5s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# launch app
ENTRYPOINT ["sh","-c","exec java -jar app.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]
