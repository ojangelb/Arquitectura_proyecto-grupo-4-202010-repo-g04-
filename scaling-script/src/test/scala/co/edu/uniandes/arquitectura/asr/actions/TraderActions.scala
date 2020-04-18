package co.edu.uniandes.arquitectura.asr.actions

import co.edu.uniandes.arquitectura.asr.config.ActionBase
import co.edu.uniandes.arquitectura.asr.utils.TianguixUtils
import co.edu.uniandes.arquitectura.asr.httpclientconfig.HttpHeadersValues
import io.gatling.core.Predef.{exec, _}
import io.gatling.http.Predef.{http, regex, jsonPath, status}

/**
  *
  * @since 1.0.0
  * @version 1.0.0
  * @date 04/04/20
  * @author <a href="js.hernandez15@uniandes.edu.co">Juan Hernández Serrato</a>
  * @company Universidad de los Andes
  *
  */
trait TraderActions extends ActionBase {

  val utils = new TianguixUtils
  private val assetEndpoint = "/asset"
  private val purchaseEndpoint = "/purchase"
  private val checkEndpoint = "/matching"

  val sell =
    exec(
      http("Publish an asset")
        .post(assetEndpoint)
        .headers(HttpHeadersValues.tianguixHeaders)
        .header("Authorization", "Bearer ${token}")
        .body(
          StringBody(session =>
            s"""{
              |	"name": "${utils.randomValues.randomName()}",
              |	"stocks": ${utils.randomValues.randomStocks()},
              |	"value": {
              |		"currency": "COP",
              |		"ammount": ${utils.randomValues.randomAmount(50,101)}
              |	},
              |	"type": "OIL_AND_GAS"
              |}""".stripMargin)
        ).asJSON
        .check(
          status.is(200),
          jsonPath("$.value.ammount").saveAs("ammount"),
          jsonPath("$.stocks").saveAs("stocks"),
          jsonPath("$.type").saveAs("type")
        )
    )

  val purchase =

      exec(
        http("Purchase an asset")
          .post(purchaseEndpoint)
          .headers(HttpHeadersValues.tianguixHeaders)
          .header("Authorization", "Bearer ${token}")
          .body(StringBody(session =>
            s"""{
             | "filters": [
             |  {
             |      "value": {
             |        "currency": "COP",
             |        "min": ${session("ammount").validate[String].map(i => i.toInt - 2).get},
             |        "max": ${session("ammount").validate[String].map(i => i.toInt + 2).get}
             |    },
             |    "stocks": {
             |      "ammount": {
             |        "min": ${session("stocks").validate[String].map(i => i.toInt - 2).get},
             |        "max": ${session("stocks").validate[String].map(i => i.toInt + 2).get}
             |      }
             |    },
             |    "type": "${session("type").as[String]}"
             |  }
             |  ]
             |}""".stripMargin
          )).asJSON
          .check(
            status.is(201),
            jsonPath("$._id").saveAs("id")
          )
      ).pause(1)

  val checkOnce =
    exec(
      http("Check asset")
        .get(checkEndpoint + "/${id}")
        .headers(HttpHeadersValues.tianguixHeaders)
        .header("Authorization", "Bearer ${token}")
        .check(
          status.is(200),
          jsonPath("$.status").saveAs("status")
        )
    )

  val check =
    exec(checkOnce)
      .asLongAs(session => session("status").validate[String].get.equals("PENDING")) {
        exec(checkOnce)
      }
}
