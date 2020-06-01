FROM oracle/graalvm-ce:20.0.0-java8

LABEL maintainer=FS_G \
	  version=1.0

COPY launcher.sh ./launcher.sh

COPY app.jar ./app.jar

RUN chmod +x ./launcher.sh

ENTRYPOINT ["/bin/sh","-c", "/launcher.sh"]
