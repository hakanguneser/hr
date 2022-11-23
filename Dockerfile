FROM openjdk:11-oracle
MAINTAINER hakan.guneser
COPY target/hr-1.0.0.jar hr-1.0.0.jar
ENTRYPOINT ["java","-jar","/hr-1.0.0.jar"]