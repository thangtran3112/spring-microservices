# Running Postgres in Docker

* Running Postgres and pgAdmin container without network, it is not working
```bash

docker run -d --name db -e POSTGRES_PASSWORD=embarkx postgres

docker run -d -e PGADMIN_DEFAULT_EMAIL=admin@admin.com -e PGADMIN_DEFAULT_PASSWORD=embarkx --name pgadmin-container dpage/pgadmin4

docker exec -it pgadmin-container ping db

docker rm -f db pgadmin-container

```

* Running Postgres and pgAdmin container Using Network

```bash
docker network create my-network

docker run -d --name db --network my-network -e POSTGRES_PASSWORD=embarkx postgres

docker run -d  --name pgadmin_container --network my-network -e PGADMIN_DEFAULT_EMAIL=admin@admin.com -e PGADMIN_DEFAULT_PASSWORD=embarkx dpage/pgadmin4

docker exec -it pgadmin_container ping db
```

* Mount a named volume for Postgres for permanent storage

```bash
docker run -d \
  --name postgres_container \
  -e POSTGRES_USER=embarkx \
  -e POSTGRES_PASSWORD=embarkx \
  -e PGDATA=/data/postgres \
  -v postgres:/data/postgres \
  -p 5432:5432 \
  --network postgres \
  --restart unless-stopped \
  postgres
```

* Start the pgAdmin service with mounted named volume
```bash
docker run -d \
--name pgadmin_container \
-e PGADMIN_DEFAULT_EMAIL=pgadmin4@pgadmin.org \
-e PGADMIN_DEFAULT_PASSWORD=admin \
-e PGADMIN_CONFIG_SERVER_MODE=False \
-v pgadmin:/var/lib/pgadmin \
-p 5050:80 \
--network postgres \
--restart unless-stopped \
dpage/pgadmin4
```

## Using Docker Compose

```bash
docker-compose up -d
docker logs <CONTAINER_NAME>
```