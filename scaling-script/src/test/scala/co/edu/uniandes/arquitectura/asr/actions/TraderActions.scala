package co.edu.uniandes.arquitectura.asr.actions

import co.edu.uniandes.arquitectura.asr.config.ActionBase
import co.edu.uniandes.arquitectura.asr.utils.TianguixUtils
import co.edu.uniandes.arquitectura.asr.httpclientconfig.HttpHeadersValues
import io.gatling.core.Predef.{exec, _}
import io.gatling.http.Predef.{http, regex, status}

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
          regex(""""stocks":([^"]*)""").saveAs("stocks"),
          regex(""""ammount":([^"]*)""").saveAs("ammount"),
          regex(""""type":"([^"]*)"""").saveAs("type")
        )
    )

  val purchase =

      exec(
        http("Purchase an asset")
          .post(purchaseEndpoint)
          .headers(HttpHeadersValues.tianguixHeaders)
          .header("Authorization", "Bearer ${token}")
          .body(
            StringBody("""{
              "filters": [
              {
              "value": {
                  "currency": "COP",
                  "min": ${"amount"} ,
                  "max": 100000000
              },
              "stocks": {
                  "ammount": {
                      "min": 1,
                      "max": 3000000
                  }
              },
              "type": "OIL_AND_GAS"
          }
      ]
  })"""
            )).asJSON
          .check(
            status.is(201),
            regex(""""_id":([^"]*)""").saveAs("id")
          )

      )
}
