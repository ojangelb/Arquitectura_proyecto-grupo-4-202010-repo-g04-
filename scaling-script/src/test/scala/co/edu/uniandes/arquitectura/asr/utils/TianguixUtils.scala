package co.edu.uniandes.arquitectura.asr.utils

import scala.util.Random

/**
  *
  * @since 1.0.0
  * @version 1.0.0
  * @date 04/04/20
  * @author <a href="js.hernandez15@uniandes.edu.co">Juan Hernández Serrato</a>
  * @company Universidad de los Andes
  *
  */
class TianguixUtils {

  object randomValues {

    def randomName(): String = randomName(15)

    def randomName(length: Int): String = scala.util.Random.alphanumeric.take(length).mkString

    def randomStocks(): Int = Random.nextInt(10000) + 500

    def randomAmount(min: Int, max: Int):Int = {
      min + Random.nextInt(max-min)
    }
  }
}
