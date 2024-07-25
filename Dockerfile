FROM openjdk:8-jdk-alpine
ADD ./structure-admin-boot/target/admin-center.jar /app/boot/admin-center.jar
ADD ./structure-admin-cloud/target/admin-center.jar /app/cloud/admin-center.jar
ADD liveness.sh /app/liveness.sh
ENV PARAMS=""
ENV JAVA_OPTS=""
ENV APP_PATH=""
ENTRYPOINT ["sh","-c","java $JAVA_OPTS $PARAMS -jar $APP_PATH","-ea","&"]
EXPOSE 7777
