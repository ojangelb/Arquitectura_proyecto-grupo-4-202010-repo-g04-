## Build

Build the docker image:

- Use de the command `docker build --build-arg JAR_FILE='build/libs/*.jar' -t asset-tianguix .`

## Run

Run the docker image:

- Use de the command `docker-compose up`

## Test

GET http://localhost:8080/

POST http://localhost:8080/

```json
{
	"firstName": "alejandro",
	"lastName" : "pereza"
}
```

