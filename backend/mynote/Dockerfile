# Use official OpenJDK 17 image
FROM eclipse-temurin:17-jdk as builder

# Set working directory
WORKDIR /app

# Copy Maven/Gradle files (to cache dependencies)
COPY pom.xml .
COPY src ./src

# Build the JAR (Maven example)
RUN ./mvnw clean package -DskipTests

# Final lightweight image
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]