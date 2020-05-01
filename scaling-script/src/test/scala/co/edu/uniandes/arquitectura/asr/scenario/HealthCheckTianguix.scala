package co.edu.uniandes.arquitectura.asr.scenario

import co.edu.uniandes.arquitectura.asr.actions.{HealthCheckActions, TraderActions}
import co.edu.uniandes.arquitectura.asr.config.ActionBase
import io.gatling.core.Predef.{scenario, _}
import io.gatling.http.Predef.http
/**
  * @since 1.0.0
  * @version 1.0.0
  * @date 04/04/20
  * @author <a href="js.hernandez15@uniandes.edu.co">Juan Hernández Serrato</a>
  * @company Universidad de los Andes
  *
  */
class HealthCheckTianguix extends ActionBase with HealthCheckActions {

  val httpConf = http.baseURL(conf.getString("baseUrl"))
  var timeToRampUsers = 300

  val userPerSecondRampOne = 3
  val userPerSecondRampTwo = 20
  val userPerSecondRampThree = 30
  val userPerSecondRampFour = 40
  val userPerSecondRampFive = 50
  val userPerSecondRampSix = 60
  val userPerSecondRampSeven = 70
  val userPerSecondRampEight = 80
  val userPerSecondRampNine = 90
  val userPerSecondRampTen = 100

  val maxResponseTimeMs = 1500
  val meanResponseTimeMs = 300

  val healthCheckTianguix = scenario("User is checking the service is up")
    .exec(
      healthCheck
    )

  setUp(
    healthCheckTianguix.inject(
      constantUsersPerSec(userPerSecondRampOne) during (timeToRampUsers),
//      constantUsersPerSec(userPerSecondRampTwo) during (timeToRampUsers),
//      constantUsersPerSec(userPerSecondRampThree) during (timeToRampUsers),
//      constantUsersPerSec(userPerSecondRampFour) during (timeToRampUsers),
//      constantUsersPerSec(userPerSecondRampFive) during (timeToRampUsers),
//      constantUsersPerSec(userPerSecondRampSix) during (timeToRampUsers),
//      constantUsersPerSec(userPerSecondRampSeven) during (timeToRampUsers),
//      constantUsersPerSec(userPerSecondRampEight) during (timeToRampUsers),
//      constantUsersPerSec(userPerSecondRampNine) during (timeToRampUsers),
//      constantUsersPerSec(userPerSecondRampTen) during (timeToRampUsers)
    ).protocols(httpConf))
     .assertions(
      global.responseTime.max.lt(maxResponseTimeMs),
      global.responseTime.mean.lt(meanResponseTimeMs),
      global.successfulRequests.percent.gt(95)
    )
}

