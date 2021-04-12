FROM openjdk:8-stretch
COPY generator/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
