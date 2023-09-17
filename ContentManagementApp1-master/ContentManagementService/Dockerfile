FROM openjdk
WORKDIR usr/lib
ENV MONGO_DATABASE="ContentManagementService"
ENV MONGO_URL="mongodb://mongoService:27017/ContentManagementService"

ADD ./target/ContentManagementService-0.0.1-SNAPSHOT.jar /usr/lib/ContentManagementService-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","ContentManagementService-0.0.1-SNAPSHOT.jar"]