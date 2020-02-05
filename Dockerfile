FROM openjdk:8


# without Filebeat and external entry point
#COPY ./target/elastic-beats-0.0.1-SNAPSHOT.jar /usr/share/proto-beats/beats-sidecar.jar
#ENTRYPOINT ["java", "-Dlogback.configurationFile=/usr/share/proto-beats/logback.xml","-jar","/usr/share/proto-beats/beats-sidecar.jar"]



# Install FileBeat 
RUN curl -L -O https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-7.5.2-amd64.deb
RUN dpkg -i filebeat-7.5.2-amd64.deb

# copy Filebeat config file
COPY ./config/filebeat/filebeat.yml /etc/filebeat/filebeat.yml

# Copy own entry-point script, so we can start both FileBeat and App
COPY ./config/docker/entrypoint.sh /bin/docker-entrypoint.sh
RUN chmod +x /bin/docker-entrypoint.sh

#provide external logging file, to control log destination
COPY ./config/docker/logback.xml /usr/share/proto-beats/logback.xml

#copy application jars.
COPY ./target/elastic-beats-0.0.1-SNAPSHOT.jar /usr/share/proto-beats/beats-sidecar.jar


CMD /bin/docker-entrypoint.sh "java -Dlogback.configurationFile=/usr/share/proto-beats/logback.xml -jar /usr/share/proto-beats/beats-sidecar.jar"

