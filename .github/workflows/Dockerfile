# ── STAGE 1: build the fat JAR with Maven & Java 21 ────────────────────
FROM maven:3.9.8-eclipse-temurin-21 AS builder
WORKDIR /app

# cache Maven dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# compile & package
COPY src ./src
RUN mvn clean package -DskipTests -B

# ── STAGE 2: minimal runtime on Corretto 21 ──────────────────────────
FROM amazoncorretto:21-alpine
WORKDIR /app

# pull in your built JAR
COPY --from=builder /app/target/*.jar app.jar

# expose the port Spring Boot listens on
EXPOSE 8080

# default environment variables (can be overridden in Render)
ENV SPRING_PROFILES_ACTIVE=prod \
    DB_DATABASE=hmsdb_acpv \
    DB_HOST=postgresql://hmsdb_acpv_user:khq3wh6HeS3TDI4n9Q3ZPpsefc6kdB2O@dpg-d08kh7adbo4c73bcg8d0-a/hmsdb_acpv \
    DB_PORT=5432 \
    DB_USER=hmsdb_acpv_user \
    DB_PASSWORD=khq3wh6HeS3TDI4n9Q3ZPpsefc6kdB2O \
    JWT_SECRET_KEY=3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b \
    JWT_EXPIRATION_TIME=360000

# optional: simple healthcheck against Spring Actuator
HEALTHCHECK --interval=30s --timeout=5s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# launch your Spring Boot app, passing through the active profile
ENTRYPOINT ["sh","-c","exec java -jar app.jar --spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]
