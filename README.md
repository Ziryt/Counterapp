# Counter API

A simple counter API demo project

## Table of Contents

- [Getting Started](#getting-started)
  - [with Docker](#with-docker)
  - [with IDE](#with-ide)
- [Usage](#usage)

[Back to top](#table-of-contents)

## Getting Started

### with Docker
👆 choice of the redaction
#### requirements
```text
- docker (or any compatible substitute)
```
#### run
Simply download the `docker-compose.yml` and run 
```
docker compose up
```

### with IDE

#### requirements:
```text
- java 21
- docker (DB cofigured with it, so it is still required)
- preferable IDE
```
#### run
Set up database also using docker compose either in console or with IDE UI assistance
Run project in IDE

[Back to top](#table-of-contents)

## Usage

Works just like any other API.
Project has OpenAPI documentation 
```
http//localhost:8080/swagger-ui/index.html#/
```
Also here is [postman collection](https://github.com/Ziryt/Counterapp/blob/main/counters.postman_collection.json) in the project for a quick start


[Back to top](#table-of-contents)
