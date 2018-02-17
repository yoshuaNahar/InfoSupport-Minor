FROM openjdk:8-jre-slim

ADD ./wait-for-it.sh /wait-for-it.sh
ADD ./target/customer*.jar /app.jar

RUN chmod +x ./wait-for-it.sh
