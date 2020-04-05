package co.edu.uniandes.arquitectura.asr.scenario

import co.edu.uniandes.arquitectura.asr.actions.{HealthCheckActions, LoginActions, TraderActions}
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
class MatchAsset extends ActionBase with LoginActions with TraderActions {

  val httpConf = http.baseURL(conf.getString("baseUrl"))
  var timeToRampUsers = 5

  val userPerSecondRampOne = 1
  val userPerSecondRampTwo = 2
  val userPerSecondRampThree = 3
  val userPerSecondRampFour = 4
  val userPerSecondRampFive = 5

  val healthCheckTianguix = scenario("Tianguix Match an asset")
    .exec(
      login,
      sell,
      purchase
    )

  setUp(
    healthCheckTianguix.inject(
      constantUsersPerSec(userPerSecondRampOne) during (timeToRampUsers),
      constantUsersPerSec(userPerSecondRampTwo) during (timeToRampUsers),
      constantUsersPerSec(userPerSecondRampThree) during (timeToRampUsers),
      constantUsersPerSec(userPerSecondRampFour) during (timeToRampUsers),
      constantUsersPerSec(userPerSecondRampFive) during (timeToRampUsers)
    ).protocols(httpConf))
}

