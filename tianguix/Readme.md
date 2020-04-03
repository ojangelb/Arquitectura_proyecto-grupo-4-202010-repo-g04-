
## Build

Build the docker image:

- Use de the command `docker build --no-cache -t tianguix .`

## Run

Run the images, node-poc and mongo, at the same time:

- Use de the command `docker-compose up`

If docker-compose takes older versions please use `docker-compose build --no-cache` before `up` command.

## Run scaled

Run the images with two tianguix images, at the same time:

- Use de the command `docker-compose up --scale tianguix=2`

If docker-compose takes older versions please use `docker-compose build --no-cache` before `up` command.

------

_Sources:_ 
- https://codeburst.io/introduction-to-docker-with-a-simple-dockerized-node-js-application-1c25680f07d7
- https://pspdfkit.com/blog/2018/how-to-use-docker-compose-to-run-multiple-instances-of-a-service-in-development/