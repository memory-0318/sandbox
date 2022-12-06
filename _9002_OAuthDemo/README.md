# 說明

## Build image

```bash
mvn package jib:dockerBuild
```

## Run daemon container

* Modify image version if you changed it in `pom.xml`

```bash
docker run -d -p 8080:8080 --name oauth_demo brian/9002_oauthdemo-dev:1.0
```

## Stop and remove container

```bash
docker stop oauth_demo
docker rm oauth_demo
```

## Remove container image

```bash
docker rmi $(docker image ls | grep 9002_oauthdemo-dev)
```