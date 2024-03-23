FROM jelastic/maven:3.9.5-openjdk-21
LABEL authors="ziryt"
COPY target/counterapp-0.0.1-SNAPSHOT.jar /data/app/app.jar
ENTRYPOINT ["java","-jar","/data/app/app.jar"]