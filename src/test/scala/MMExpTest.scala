package MMExp

import org.scalatest.FunSuite

/**
  * Created by eugenel on 5/13/17.
  */
class MMExpTest extends FunSuite{

  //Tel-Aviv NewYork 9135km
  val TA_NY = 9135
  //900 km/hour
  val Boing747_speed = 900
  //Usually wind between 20 km/h to 40 km/h
  val strongWind =  new Plane2Directions(Boing747_speed, 70, TA_NY)
  println("Strong Wind:")
  println(strongWind)
  println(strongWind.totalTime + " hours")

  val veakWind = new Plane2Directions(Boing747_speed, 5, TA_NY)
  println("Veak Wind:")
  println(veakWind)
  println(veakWind.totalTime + " hours")

  val nWind = new Plane2Directions(Boing747_speed, 30, TA_NY)
  println("Normal Wind:")
  println(nWind)
  println(nWind.totalTime + " hours")

  val noWind = new Plane2Directions(Boing747_speed, 0, TA_NY)
  println("No Wind:")
  println(noWind)
  println(noWind.totalTime + " hours")

}
