FROM eclipse-temurin:17-jdk
WORKDIR /app

# copy maven wrapper & pom
COPY mynote/mvnw pom.xml ./
COPY mynote/.mvn .mvn

# pre-fetch deps
RUN ./mvnw dependency:go-offline -B

# copy and build
COPY mynote/src src
RUN ./mvnw package -DskipTests -B

EXPOSE 8080
CMD ["java","-jar","target/*.jar"]
