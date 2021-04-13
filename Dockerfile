FROM openjdk:8-alpine
COPY generator/target/*.jar /usr/src/myapp/app.jar
COPY generator/src/main/resources/ /usr/src/myapp/code-template
WORKDIR /usr/src/myapp
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar","--code-generator.outputPath=/usr/src/myapp/output","--code-generator.templatePath=/usr/src/myapp/code-template"]
#ENTRYPOINT ["java","-Dloader.path=.,resources,lib","-jar","epp-web-host-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]


# docker run -e "SPRING_PROFILES_ACTIVE=prod" -e "SPRING_DATASOURCE_URL=jdbc:postgresql://172.31.2.78:54323/postgres?currentSchema=public" -e "SPRING_REDIS_HOST=172.31.2.78" -p 8080:8081 -t epp-runtime:v0.1
