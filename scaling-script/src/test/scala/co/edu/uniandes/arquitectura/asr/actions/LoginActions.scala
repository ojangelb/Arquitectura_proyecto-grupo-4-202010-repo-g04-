package co.edu.uniandes.arquitectura.asr.actions

/**
  *
  * @since 1.0.0
  * @version 1.0.0
  * @date 8/7/19
  * @author <a href="pinergio@colpatria.com">Giovanni Piñeros Mora</a>
  * @company Digital Factory Colombia- Colpatria Multibanca
  *
  */

import co.edu.uniandes.arquitectura.asr.config.ActionBase
import co.edu.uniandes.arquitectura.asr.httpclientconfig.HttpHeadersValues
import io.gatling.core.Predef.{feed, _}
import io.gatling.http.Predef.{http, regex, status}

trait LoginActions extends ActionBase {

  private val loginURL = "/login"

  val tianguixUsers = csv("tianguixUsers.csv").circular

  val login =
    feed(tianguixUsers)
      .exec(
        http("User do login in Tianguix")
          .post(loginURL)
          .headers(HttpHeadersValues.tianguixHeaders)
          .body(
            StringBody("""{"user":"${username}","pass":"${password}"}""".stripMargin)
          ).asJSON
          .check(
            status.is(200),
            regex(""""token":"([^"]*)"""").saveAs("token")
          )
      )

}
