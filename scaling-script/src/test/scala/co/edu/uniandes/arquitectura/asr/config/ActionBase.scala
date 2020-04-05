package co.edu.uniandes.arquitectura.asr.config

import com.typesafe.config.ConfigFactory
import io.gatling.core.scenario.Simulation

class ActionBase extends Simulation {
  implicit val conf = ConfigFactory.load()
}
