FROM openjdk:8-jdk
VOLUME /var/lib/amazon-vendor-service
ADD fw-amazon-common/target/fw-amazon-common-1.0.0.jar /var/lib/amazon-vendor-service/fw-amazon-common-1.0.0.jar
ADD fw-amazon-api/target/fw-amazon-api-1.0.0.jar /var/lib/amazon-vendor-service/app.jar 
#ADD target/demo-0.0.1-SNAPSHOT.jar /var/lib/amazon-vendor-service/app.jar
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"
EXPOSE 8080 8089
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /var/lib/amazon-vendor-service/app.jar" ]
