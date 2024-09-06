# Company microservice

## Setup
* Run `docker compose up -d` to start the Postgres and pgAdmin containers
* Package the application into a JAR file under `target` folder
```bash
mvn clean package
./mvmw package 
```
Specify packaging type (default = `jar`, other options is `war` and `ear`)
```pom.xml
<packaging>jar</packaging>
```

* Running the application with `jar`:
```bash
    jar -tf ./target/companyms-0.0.1-SNAPSHOT.jar
    java -jar ./target/companyms-0.0.1-SNAPSHOT.jar
```
This will require `jdk` path to be set in .zshrc
## 