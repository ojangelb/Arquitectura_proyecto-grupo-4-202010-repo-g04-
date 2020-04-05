package co.edu.uniandes.arquitectura.asr.actions

import co.edu.uniandes.arquitectura.asr.config.ActionBase
import co.edu.uniandes.arquitectura.asr.httpclientconfig.HttpHeadersValues
import io.gatling.core.Predef.{exec, _}
import io.gatling.http.Predef.{http, status}

/**
  *
  * @since 1.0.0
  * @version 1.0.0
  * @date 04/04/20
  * @author <a href="js.hernandez15@uniandes.edu.co">Juan Hernández Serrato</a>
  * @company Universidad de los Andes
  *
  */
trait HealthCheckActions extends ActionBase {

  private val healthURI = "/healthcheck"

  val healthCheck =
    exec(
      http("Check the system is up")
        .get(healthURI)
        .headers(HttpHeadersValues.tianguixHeaders)
        .disableFollowRedirect
        .check(status.is(200))
    )

}
