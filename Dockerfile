FROM adoptopenjdk:11-jre-hotspot
WORKDIR /AssignmentProject
ADD target/AssignmentProject.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "AssignmentProject.jar"]

