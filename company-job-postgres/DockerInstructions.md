* Dockerize with Maven Build:

```bash
./mvnw spring-boot:build-image "-Dspring-boot.build-image.imageName=thangtran3112/jobapiimage"
docker push thangtran3112/jobapiimage

```

* Other Docker commands
```bash
    docker run -it -d -p 8080:8080  --name jobapicontainer  thangtran3112/jobapiimage
    docker rm jobapicontainer
    docker rmi thangtran3112/jobapiimage
    docker ps
    docker ps -a
    docker images
    docker exec -it jobapicontainer bash
    docker build -t thangtran3112/jobapiimage .
    docker logs jobapicontainer
    docker inspect jobapicontainer
```

