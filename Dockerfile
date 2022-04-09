FROM gradle:7.1-jdk16 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle :plugins:shadowJar --no-daemon

FROM openjdk:16
EXPOSE 9000:9000
RUN mkdir /app
COPY --from=build /home/gradle/src/plugins/build/libs/plugins-all.jar /app/ase-project.jar
ENTRYPOINT ["java","-jar","/app/ase-project.jar"]
