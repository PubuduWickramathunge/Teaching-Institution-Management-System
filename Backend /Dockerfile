  FROM  openjdk:8-alpine
  ADD build/libs/*.jar backend.jar
  EXPOSE 8080
  ENTRYPOINT ["java","-jar","/backend.jar"]