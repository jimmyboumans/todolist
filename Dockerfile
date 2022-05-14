# docker build -t todolist .
# docker run --name todolist -d -p 8080:8080 --rm todolist
FROM adoptopenjdk:11-jre-openj9

RUN mkdir /opt/app

COPY /target/todo-0.0.1-SNAPSHOT.jar /opt/app/todo-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "/opt/app/todo-0.0.1-SNAPSHOT.jar"]