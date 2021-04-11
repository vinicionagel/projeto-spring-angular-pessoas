FROM adoptopenjdk:14-jre

ENV PESSOA_HOME /usr/local/pessoa
ENV PATH $PESSOA_HOME:$PATH
WORKDIR $PESSOA_HOME

ADD target/envolti*.jar ./api.jar

EXPOSE 8080
CMD ["java", "-jar", "-Dspring.profiles.active=prod","api.jar"]
