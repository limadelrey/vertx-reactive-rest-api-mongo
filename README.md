# Reactive REST API using Java, Vert.x, MongoDB, RxJava2 and Docker

This repository provides a template for building a completely self-contained reactive micro-service using a REST API as a proof of concept.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

```
Java 11
Maven 3.6.0
Docker 2.0.0.3
```

### Running

#### Create "book-store-network"
```bash
$ docker network create book-store-network
```

#### Build fat .jar

```bash
$ mvn clean package
```

#### Run containers

```bash
$ docker-compose build
$ docker-compose up
```

#### Import Postman collection
Open Postman, import "book-store.postman_collection.json" and you should be able to create, read, update and delete books asynchronously in a reactive way in a self-contained environment.

#### Run Postman collection
After importing "book-store.postman_collection.json" you should be able to run the provided collection. This collection includes some tests on book-store REST API.

## Built With

* [Java](https://www.java.com/)
* [Vert.x](https://vertx.io/)
* [RxJava 2](http://reactivex.io/)
* [Maven](https://maven.apache.org/)
* [MongoDB](https://www.mongodb.com/)
* [Docker](https://www.docker.com/)
* [Postman](https://www.getpostman.com/)
