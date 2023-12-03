FROM openjdk:17
COPY target/Windsurfers-Weather-Service-0.0.1-SNAPSHOT.jar /windsurfers.jar
ENTRYPOINT ["java","-jar","/windsurfers.jar"]