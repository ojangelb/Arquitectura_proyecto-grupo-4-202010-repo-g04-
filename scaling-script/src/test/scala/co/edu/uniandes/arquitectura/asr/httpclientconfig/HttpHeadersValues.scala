package co.edu.uniandes.arquitectura.asr.httpclientconfig

import com.typesafe.config.ConfigFactory

object HttpHeadersValues {

  val conf = ConfigFactory.load();
  val baseUrl = conf.getString("baseUrl")
  val headerAccept = "application/json, text/plain, */*"

  val tianguixHeaders = Map(
    "Cache-Control" -> "no-cache",
    "User-Agent" -> "Gatling/Tianguix Test App",
    "Accept" -> "*/*",
    "Connection" -> "keep-alive"
  )

}
