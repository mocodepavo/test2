FROM openjdk:8-jdk-alpine

LABEL maintainer=FS_G \
	  version=1.0

COPY dockerfile/alpine/basic/launcher.sh ./launcher.sh

COPY dockerfile/alpine/basic/app.jar ./app.jar

RUN chmod +x ./launcher.sh

ENTRYPOINT ["/bin/sh","-c", "/launcher.sh"]
