ARG BUILDPLATFORM
ARG TARGETPLATFORM

FROM --platform=$BUILDPLATFORM gradle:9-jdk25 AS BUILD_STAGE
WORKDIR /tmp
COPY .git .git
COPY gradle gradle
COPY build.gradle.kts gradle.properties settings.gradle.kts gradlew ./
COPY src src
RUN chmod +x gradlew
RUN ./gradlew --no-daemon --info test buildFatJar

FROM --platform=$TARGETPLATFORM eclipse-temurin:25-jre-alpine
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=BUILD_STAGE /tmp/build/libs/*-all.jar /app/ktor-server.jar
ENTRYPOINT ["java","-Xlog:gc+init","-XX:+PrintCommandLineFlags","-jar","/app/ktor-server.jar"]
