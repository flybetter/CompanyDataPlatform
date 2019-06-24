FROM anapsix/alpine-java
COPY febs.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


# sudo docker build  -t flybetter/companydataplatform:v1 .
# sudo docker run -d -p 8080:8080 flybetter/companydataplatform:v1