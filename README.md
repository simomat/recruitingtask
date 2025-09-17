
## Requirements

 * local docker installation with compose support
 * bash to execute the build script

## Build Spring Boot app docker image

```sh
# in case it's not executable already
chmod +x build_and_import_spring_boot_app.sh
./build_and_import_spring_boot_app.sh*
```

The build is done by a docker image itself. Thus, only docker is needed to build the application.
During the build, the image is imported into docker and is named `employment-monitored-spring-boot-app:latest`.

## Run the application along with prometheus and grafana

```sh
# start containers into the background
docker compose up -d
```

## Watch the monitoring dashboard


Visit [the Grafana dashboard](http://localhost:3000/d/oS9VJB_Wz11/jvm-and-custom-metric?orgId=1&from=now-5m&to=now&timezone=browser&var-datasource=PBFA97CFB590B2093&var-namespace=$__all&var-application=$__all&var-podname=$__all&refresh=5s) and login with user `admin` and password `grafana`.

It will take some time until the dashboard shows some data.


## Cleanup

```sh
# stop the containers
docker compose down

# delete the volume that contains prometheus data
docker volume rm devops_recruiting_prometheus_volume

# delete the images that have been pulled or built
docker rmi \
  employment-monitored-spring-boot-app:latest \
  grafana/grafana:main-ubuntu \
  prom/prometheus:main \
  gradle:jdk21-noble
```
