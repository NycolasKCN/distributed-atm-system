FROM amazoncorretto:21.0.2-alpine3.19
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean -Dmaven.test.skip
RUN ./mvnw install -Dmaven.test.skip
EXPOSE 8080
CMD ["java", "-jar", "-Dspring.profiles.active=prod", "./target/DistributedATM.jar"]
