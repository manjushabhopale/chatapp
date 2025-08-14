FROM openjdk:21-jdk

WORKDIR /app

COPY  target/chatroom-0.0.1-SNAPSHOT.jar chatroom-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/app/chatroom-0.0.1-SNAPSHOT.jar"]


