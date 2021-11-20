FROM openjdk:8
ADD target/demo-workshop-1.0.0.jar demo-workshop.jar
ENTRYPOINT ["java", "-jar", "demo-workshop.jar"]
# Commit Test

