FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/youtube-downloader*.jar youtube-downloader.jar
ENV JAVA_OPTS=""

ENV BUILD_NO BUILD_NO
RUN echo ${BUILD_NO}

EXPOSE 8080

ENTRYPOINT exec java $JAVA_OPTS -jar youtube-downloader.jar