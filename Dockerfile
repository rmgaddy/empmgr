FROM maven:3.3-jdk-8

RUN mkdir /empmgr

COPY target/empmgr-0.0.1-SNAPSHOT.jar /empmgr
COPY bin/entry-point.bash /empmgr

EXPOSE 8080/tcp

ENTRYPOINT ["/bin/bash"]

CMD ["/empmgr/entry-point.bash"]
