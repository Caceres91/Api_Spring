FROM openjdk:8
ADD target/desarrollo-0.0.1-SNAPSHOT.jar desarrollo.jar
EXPOSE 4000
ENTRYPOINT ["java","-jar","desarrollo.jar"]