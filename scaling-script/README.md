# Run performance test with gatling.

## Initial configuration

Add to your `hosts` file (`/private/etc/hosts` in MacOs) the following line:

```
127.0.0.1 tianguix.com
```

## Run scenario in Terminal:

```
mvn gatling:execute -Dgatling.simulationClass=co.edu.uniandes.arquitectura.asr.scenario.HealthCheckTianguix
```


## Run scenario in option Intellij setup:

Folder setup: 

- Run -> Edit Configurations -> Working directory

And add the follow command in "Command line": 
```
gatling:execute -Dgatling.simulationClass=co.edu.uniandes.arquitectura.asr.scenario.HealthCheckTianguix
```