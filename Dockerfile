FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY . /app

RUN javac App.java

CMD ["java", "App"]