# spring-microservices

Microservices with Spring Boot and deployment on k8s

## Architecture

- ![architecture](./architecture.png)

## Zipkin

- [Using Docker to run Zipkin instance](https://zipkin.io/pages/quickstart.html)

```
  docker run -d -p 9411:9411 openzipkin/zipkin
```

- Add Zipkin configs to Docker compose:

```yaml
zipkin:
  image: openzipkin/zipkin
  container_name: zipkin
  ports:
    - 9411:9411
  networks:
    - postgres
```

- Using docker compose: `docker-compose up -d`

- Zipkin, in Production we should not track every request with probability 1.0

## Micrometer

- Micrometer provide metrics collection for your applications
- Micrometer is vendor agnostic, it abstracts all the metrics from your application with integrations to various cloud providers, such as Datadog, Prometheus, New Relic, Grafana, etc.

## If using with OpenFeign, need extra dependency

```pom.xml
  <dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
  </dependency>
  <dependency>
    <groupId>io.zipkin.reporter2</groupId>
    <artifactId>zipkin-reporter-brave</artifactId>
  </dependency>
  <dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-micrometer</artifactId>
  </dependency>
```

## Setup

- Run `docker compose up -d` to start the Postgres and pgAdmin containers
- Package the application into a JAR file under `target` folder

```bash
  mvn clean package
  ./mvmw package
```

Specify packaging type (default = `jar`, other options is `war` and `ear`)

```pom.xml
<packaging>jar</packaging>
```

- Running the application with `jar` locally

```bash
    jar -tf ./target/companyms-0.0.1-SNAPSHOT.jar
    java -jar ./target/companyms-0.0.1-SNAPSHOT.jar
```

This will require `jdk` path to be set in .zshrc

## Dockerize microservices

```bash
  ./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=thangtran3112/servicereg"

  ./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=thangtran3112/companyms"

  ./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=thangtran3112/reviewms"

  ./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=thangtran3112/jobms"

  ./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=thangtran3112/gateway-ms"

  ./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=thangtran3112/config-server-ms"
```

- Pushing images to DockerHub

```bash
  docker push thangtran3112/servicereg
  docker push thangtran3112/companyms
  docker push thangtran3112/reviewms
  docker push thangtran3112/jobms
  docker push thangtran3112/gateway-ms
  docker push thangtran3112/config-server-ms
```

## Troubleshoot

- Make sure the database must be up and running first, before dockerize other services. Otherwise, config-server-ms and gateway-ms will not grab the correct configurations.

- Make sure to match `- SPRING_PRO`FILES_ACTIVE=docker in `docker-compose.yaml` with `application-docker.properties` of the corresponding service

- If Port 5432 is in use, by other applications, other other postgres containers:

```bash
sudo launchctl list | fgrep postg
kill -9 <PID>
```

## K8S with minikube

```bash
  kubectl apply -f ./k8s/services/postgres/
```

- Creating databases for our application, running the command into the container:

```bash
  minikube stop
  minikube start
  kubectl exec -it postgres-0 -- psql -U embarkx
```

```psql
  \list
  create database job;
  create database review;
  create database company;
```

The part after `--` will be the commandline, to be executed inside the container.
In this case, it is `psql -U embarkx`

```bash
  kubectl apply -f ./k8s/bootstrap
```

- Run `kubectl apply -f ./k8s/bootstrap` to run all deployment yaml files within `bootstrap` folder
