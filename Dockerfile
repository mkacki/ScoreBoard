FROM maven:3-amazoncorretto-17-debian-bookworm

ARG MAVEN_GOAL="clean package"
WORKDIR /workspace
CMD mvn $MAVEN_GOAL
