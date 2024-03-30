FROM jelastic/maven:3.9.5-openjdk-21

LABEL authors="ziryt"
LABEL org.opencontainers.image.source https://github.com/Ziryt/Counterapp
COPY target/counterapp-0.0.1-SNAPSHOT.jar /data/app/app.jar
ENTRYPOINT ["java","-jar","/data/app/app.jar"]