# Product Management and Review Application

This is an academic project consisting of a migration of a monolithic Java Spring REST application into a microservice architecture using RabbitMQ as the message broker between microservices. 

A layered architecture was used for all the services, looking to fulfill the SOLID principles.

# How to run

The project contains a docker-compose.yml file that will create docker containers for the microservices, the monolith, their databases and the message broker.

To run create the docker containers use the command:

```sh
docker compose up -d
```
