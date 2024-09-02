# spring-microservices

Microservices with Spring Boot and deployment on k8s

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
