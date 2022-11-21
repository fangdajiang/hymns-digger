FROM liferay/jdk11:latest

MAINTAINER "fangdajiang@gmail.com"
LABEL description="Hymns Digger Backend"

ENV JAVA_APP_JAR hymns-digger.jar
ENV JAVA_APP_DIR /app/

COPY target/${JAVA_APP_JAR} ${JAVA_APP_DIR}

ENTRYPOINT ["java", "-jar", "/app/hymns-digger.jar"]