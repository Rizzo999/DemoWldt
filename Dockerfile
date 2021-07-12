#FROM jimador/docker-jdk-8-maven-node
#ENV MAVEN_OPTS -Xms256m -Xmx256m
#RUN mkdir -p /usr/local/src/mvnapp
#WORKDIR /usr/local/src/mvnapp
#ADD . /usr/local/src/mvnapp
#RUN mvn -Dmaven.test.skip=true clean install
#CMD mvn exec:java -Dexec.mainClass="it.unimore.dipi.iot.wldt.process.WldtProcess"

FROM jimador/docker-jdk-8-maven-node

ENV MAVEN_OPTS -Xms16m -Xmx32m

RUN mkdir -p /usr/local/src/mvnapp
WORKDIR /usr/local/src/mvnapp
ADD . /usr/local/src/mvnapp

RUN mvn -Dmaven.test.skip=true clean install dependency:copy-dependencies

WORKDIR /usr/local/src/mvnapp
RUN chmod +x run.sh

#CMD ./run.sh wldt.WldtMqttProcess
CMD ./run.sh wldt.WldtMqttProcessWithConf
