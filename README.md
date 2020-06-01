# SEAT - Microservice Vehicle Management Tool - Demo App

El proyecto **Microservice Vehicle Management Tool** forma parte de la demo técnica del GITAM 2020 y su responsabilidad es la de gestionar el alta, baja y modificación de ordenes comerciales de vehículos.

Las ordendes comerciales recibidas se persisten localmente en una base de datos H2 y se genera un evento de tipo "Command" hacia el microservicio **Microservice Traking Tool** que es el responsable del alta y la gestión de estado de las ordenes de fabricación. Este microservicio también se subscribe al canal de eventos expuesto por el microservicio **Microservice Vehicle Management Tool** para recibir a través de él todos los eventos relacionados con las ordenes comerciales ya tramitadas.

De la misma manera el microservicio **Microservice Vehicle Management Tool** expone un canal de Errores.

## Prerequisitos

Para poder realizar la construcción del proyecto será necesario tener instalado en el equipo:

- Java 1.8+
- Maven 3.6.0+
- Git 2.19.2+


Para poder ejecutar este microservicio se necesita la siguiente lista de componentes instalados en la plataforma de Openshift:

- **AMQ Streams:** Componente responsable de gestionar la comunicación asíncrona entre los diferentes microservicios que conforman la solución. El despliegue de este componente deberá realizarse a través del uso del Operador que Redhat tiene para esa finalidad.

- **Community Jaeger Operator:** Componente responsable de gestionar todas las trazas distribuidas que piuedan generarse en los diferentes microservicios que conforman la solución. El despliegue de este componente deberá realizarse mediante el uso del Operador que Redhat tiene para esa finalidad.

Para poder acceder a la consola de la solución se deberá utilizar la siguiente URL:

https://jaeger-all-in-one-inmemory-track-vehicle-data.apps-crc.testing/search


## Construcción del proyecto

Una vez validado el entorno y que todas las herramientas necesarias se encuentran funcionando correctamente se podrá proceder a la construcción del proyecto.

Para realizar la construcción del proyecto primero se deberá descargar el código desde el repositorio GIT de EAM. Para ello se deberá ejecutar el siguiente comando:

```bash
[admin@localhost kubernetes]$ git clone https://group-cip.audi.de/stash/projects/SEEAMC/repos/seat-eam-concepts/browse/demo-gitam-2020/micro-vehicle-management-tool
```

Una vez descargado el proyecto se usará Maven para compilar el proyecto. Hay que tener en cuenta que previamente se ha debido construir los proyectos "dependencies" y "core". Para más información al respecto consultar las siguentes URLs respectivamente:

**Dependencies**

https://group-cip.audi.de/stash/projects/SEEAMC/repos/seat-eam-spring-boot-microservices-chassis-dependecies/browse

**Core**

https://group-cip.audi.de/stash/projects/SEEAMC/repos/seat-eam-spring-boot-microservices-chassis-core/browse


En el POM del proyecto hay **dos perfiles** definidos: **LOCAL** y **PROD**

En el caso del perfil de **LOCAL** se excluye del JAR resultante el XML correspondiente a LOGBACK y que permite que los logs se muestren por Consola.

En el caso del perfil de **PROD** se excluye del JAR resultante los ficheros YAML (YML) de configuración, el fichero SQL con el ejemplo de BBDD H2, el fichero XML de LOGBACK y el fichero CONF de LOGBACK para la inicialización de LOGSTASH

para usar los diferentes perfiles se deberán usar las directivas **-PLOCAL** y **-PPROD** respectivamente.

Por ejemplo, a continuación se realiza la construcción del proyecto con la configuración del perfil **LOCAL**

```bash
sarcoshs-MacBook-Pro:kubernetes sarcosh$ cd seat-eam-concepts/demo-gitam-2020/micro-vehicle-management-tool

iMac-de-Sergio:micro-vehicle-management-tool sarcosh$ mvn clean install -DskipTests -PLOCAL
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------< com.seat.micro:micro-vehicle-management-tool >------------
[INFO] Building micro-vehicle-management-tool 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ micro-vehicle-management-tool ---
[INFO] Deleting /Users/sarcosh/Documents/kubernetes/seat-eam-concepts/demo-gitam-2020/micro-vehicle-management-tool/target
[INFO] 
[INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ micro-vehicle-management-tool ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 3 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:compile (default-compile) @ micro-vehicle-management-tool ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 14 source files to /Users/sarcosh/Documents/kubernetes/seat-eam-concepts/demo-gitam-2020/micro-vehicle-management-tool/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:3.1.0:testResources (default-testResources) @ micro-vehicle-management-tool ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/sarcosh/Documents/kubernetes/seat-eam-concepts/demo-gitam-2020/micro-vehicle-management-tool/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.8.1:testCompile (default-testCompile) @ micro-vehicle-management-tool ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 1 source file to /Users/sarcosh/Documents/kubernetes/seat-eam-concepts/demo-gitam-2020/micro-vehicle-management-tool/target/test-classes
[INFO] 
[INFO] --- maven-surefire-plugin:2.22.2:test (default-test) @ micro-vehicle-management-tool ---
[INFO] Tests are skipped.
[INFO] 
[INFO] --- maven-jar-plugin:3.1.2:jar (default-jar) @ micro-vehicle-management-tool ---
[INFO] Building jar: /Users/sarcosh/Documents/kubernetes/seat-eam-concepts/demo-gitam-2020/micro-vehicle-management-tool/target/app.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:2.2.4.RELEASE:repackage (repackage) @ micro-vehicle-management-tool ---
[INFO] Replacing main artifact with repackaged archive
[INFO] 
[INFO] --- spring-boot-maven-plugin:2.2.4.RELEASE:repackage (default) @ micro-vehicle-management-tool ---
[INFO] Replacing main artifact with repackaged archive
[INFO] 
[INFO] --- maven-install-plugin:2.5.2:install (default-install) @ micro-vehicle-management-tool ---
[INFO] Installing /Users/sarcosh/Documents/kubernetes/seat-eam-concepts/demo-gitam-2020/micro-vehicle-management-tool/target/app.jar to /Users/sarcosh/.m2/repository/com/seat/micro/micro-vehicle-management-tool/0.0.1-SNAPSHOT/micro-vehicle-management-tool-0.0.1-SNAPSHOT.jar
[INFO] Installing /Users/sarcosh/Documents/kubernetes/seat-eam-concepts/demo-gitam-2020/micro-vehicle-management-tool/pom.xml to /Users/sarcosh/.m2/repository/com/seat/micro/micro-vehicle-management-tool/0.0.1-SNAPSHOT/micro-vehicle-management-tool-0.0.1-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.552 s
[INFO] Finished at: 2020-03-20T17:24:27+01:00
[INFO] ------------------------------------------------------------------------
```

En el caso de ser necesario este JAR se deberá persistir en un repositorio de artefactos (Nexus) como parte de la infraestructura de CI/CD usada por los proyectos

Para ello se deberá modificar la condiguración actual del POM del proyecto para que esta haga una referencia correcta al respositorio de artefactos deseado. Concretamente la configuración que ha de modificarase es la siguiente:

```bash
   <distributionManagement>
        <snapshotRepository>
            <id>nexus-snapshots</id>
            <url>
                http://seat-nexus3-seat-ci-cd.${ENVIRONMENT_NAME}.aws.seat.cloud.vwgroup.com/repository/modularBackend-DES/
            </url>
        </snapshotRepository>
        <repository>
            <id>nexus-releases</id>
            <url>
                http://seat-nexus3-seat-ci-cd.${ENVIRONMENT_NAME}.aws.seat.cloud.vwgroup.com/repository/modularBackend-EXP/
            </url>
        </repository>
    </distributionManagement> 
```

Una vez finalizada la construcción del proyecto se deberá proceder a la construcción de la imagen Docker de la solución.

## Construcción de la imágen Docker

Para construir la imágen Docker del proyecto se deberán seguir los siguientes pasos.

Primero se deberá verificar que la configuración de la aplicación se encuentra correctamente configurada en los ficheros de despliegue (ya que la configuración NO se incluirá dentro de JAR de la aplicación).

Para ello se deberá revisar el contenido del fichero **00-configmap.yaml** y se deberá verificar que las configuraciones aplicadas por entorno son las correctas.

```bash
iMac-de-Sergio:micro-vehicle-management-tool sarcosh$ cat ./deployment/local/00-configmap.yaml 
kind: ConfigMap
apiVersion: v1
metadata:
  name: local-configmap-micro-vehicle-management-tool
  namespace: track-vehicle-data
data:
  application.yml: |-
    server:
      port: 8090

    spring:
      application.name: Micro Vehicle Management Tool
      
      kafka:
        bootstrap-servers:
        - kafka-cluster-kafka-bootstrap:9092
        consumer:
          group-id: micro-vehicle-management-tool-group
        command:
          topic: micro-vehicle-management-tool-command-topic
        query:
          topic: micro-vehicle-management-tool-query-topic
        response:
          topic: micro-vehicle-management-tool-response-topic
        event:
          topic: micro-vehicle-management-tool-event-topic
        error:
          topic: micro-vehicle-management-tool-error-topic
          
      datasource:
        #initialize: false
        
        url: jdbc:h2:mem:testdb
        driverClassName: org.h2.Driver
        username: sa
        password: password
        
       #schema: create-db.sql
       #data: seed-data.sql 
        
      #jpa:
      #  database-platform: org.hibernate.dialect.H2Dialect
        
      h2.console.enabled: true
      h2.console.path: /h2-console

    logging:
      level:
        com.seat.micro: INFO
      file: /tmp/app.log
        
    opentracing:
      jaeger:
        enabled: true
        enable-b3-propagation: true
        http-sender:
         url: http://jaeger-all-in-one-inmemory-collector:14268/api/traces
      spring:
        web:
          enabled: true
```

Una vez verificada que la configuración es la adecuada se deberá copiar el fichero resultante de la construcción de la aplicación **(app.jar)** en el directorio donde se encuentra el Dockerfile del proyecto.


```bash
iMac-de-Sergio:micro-vehicle-management-tool sarcosh$ cp ./target/app.jar ./dockerfile/alpine/basic/
iMac-de-Sergio:micro-vehicle-management-tool sarcosh$ 
```

Una vez copiado se deberá verificar que el contenido del directorio **dockerfile** sea el siguiente:


```bash
iMac-de-Sergio:micro-vehicle-management-tool sarcosh$ ls -lrt ./dockerfile/alpine/basic/
total 122048
-rw-r--r--  1 sarcosh  staff        67 14 mar 13:40 launcher.sh
-rw-r--r--  1 sarcosh  staff       195 17 mar 16:12 Dockerfile
-rwxr-xr-x@ 1 sarcosh  staff       440 19 mar 15:28 build.sh
-rw-r--r--  1 sarcosh  staff  62474663 23 mar 08:35 app.jar
```

La construcción de contenedor Docker se deberá realizar a través del script ("build.sh") que será el responsable de realizar la construcción de la imagen y su subida al Cluster de Openshift. Hay que tener en cuenta que en función del entorno donde se esté trabajando (LOCAL, DEV, PROD) se deberán particularizar los valores del mismo (usuario/password, URL del registro de imagenes Docker,...) 

A continuación se muestra una ejecución script:

```bash
iMac-de-Sergio:basic sarcosh$ ./build.sh 
Login successful.

You have access to 56 projects, the list has been suppressed. You can list all projects with 'oc projects'

Using project "track-vehicle-data".
Sending build context to Docker daemon  62.48MB
Step 1/6 : FROM openjdk:8-jdk-alpine
8-jdk-alpine: Pulling from library/openjdk
e7c96db7181b: Pull complete 
f910a506b6cb: Pull complete 
c2274a1a0e27: Downloading [=======>                                           ]   11.2MB/70.73MB

 ...
```

Una vez la imagen se haya cargado correctamente en el repositorio de imagenes Docker del Cluster Openshift (u otro accesible por el Cluster) se podrá proceder al despliegue del proyecto.


## Despliegue del proyecto en Kubernetes

Para desplegar la solución se deberán ejecutar los siguientes comandos:

```bash
iMac-de-Sergio:local sarcosh$ pwd
/Users/sarcosh/Documents/kubernetes/seat-eam-concepts/demo-gitam-2020/micro-vehicle-management-tool/deployment/local
iMac-de-Sergio:local sarcosh$ ls -lrt
total 40
-rw-r--r--  1 sarcosh  staff   347 14 mar 13:40 04-deployment-hpa.yaml
-rw-r--r--  1 sarcosh  staff   268 19 mar 17:58 02-service.yaml
-rw-r--r--  1 sarcosh  staff   291 19 mar 20:49 03-route.yaml
-rw-r--r--  1 sarcosh  staff  1507 20 mar 10:48 01-deployment.yaml
-rw-r--r--  1 sarcosh  staff  1531 23 mar 08:34 00-configmap.yaml

iMac-de-Sergio:local sarcosh$ oc apply -f 00-configmap.yaml

iMac-de-Sergio:local sarcosh$ oc apply -f 01-deployment.yaml

iMac-de-Sergio:local sarcosh$ oc apply -f 02-service.yaml

iMac-de-Sergio:local sarcosh$ oc apply -f 03-route.yaml

iMac-de-Sergio:local sarcosh$ oc apply -f 04-deployment-hpa.yaml
```

Una vez validado que el despliegue se ha realizado correctamente se puede acceder a los servicios publicados por el proyecto "Tracking Vehicle Data"


```bash
iMac-de-Sergio:local sarcosh$ curl -vvv -location --request GET 'http://micro-vehicle-management-tool-route-track-vehicle-data.apps-crc.testing/api/v1/orders' --header 'Content-Type: application/json' --header 'Cache-Control: no-cache'

Note: Unnecessary use of -X or --request, GET is already inferred.
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0*   Trying 192.168.64.2...
* TCP_NODELAY set
* Connected to micro-vehicle-management-tool-route-track-vehicle-data.apps-crc.testing (192.168.64.2) port 80 (#0)
> GET /api/v1/orders HTTP/1.1
> Host: micro-vehicle-management-tool-route-track-vehicle-data.apps-crc.testing
> User-Agent: curl/7.64.1
> Accept: */*
> Content-Type: application/json
> Cache-Control: no-cache
> 
< HTTP/1.1 200 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Mon, 23 Mar 2020 08:05:39 GMT
< Set-Cookie: a201f0f34a286679025bebe7d91f2233=f7fef67aecb5cb40ed8501fd464d7b0d; path=/; HttpOnly
< Cache-control: private
< 
{ [338 bytes data]
100   326    0   326    0     0  17157      0 --:--:-- --:--:-- --:--:-- 17157
* Connection #0 to host micro-vehicle-management-tool-route-track-vehicle-data.apps-crc.testing left intact
* Closing connection 0
```
