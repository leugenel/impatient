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

class LorentzTransformTest extends FunSuite{

  //Distance X measured in light-seconds, time in seconds c=1 light-second/second
  //Velocity, Xr, Tr, c
  cprint(new LorentzTransform(0.1, 0, 10, 1))
  cprint(new LorentzTransform(0.1, 0, 20, 1))
  cprint(new LorentzTransform(0.5, 0, 10, 1))
  cprint(new LorentzTransform(0.5, 0, 20, 1))
  cprint(new LorentzTransform(0.99, 0, 10, 1))
  cprint(new LorentzTransform(0.99, 0, 20, 1))
  cprint(new LorentzTransform(0.5, 100, 0, 1))
  cprint(new LorentzTransform(0.5, 100, 10, 1))
  cprint(new LorentzTransform(0.5, 100, 20, 1))
  cprint(new LorentzTransform(0.5, 1000, 0, 1))
  cprint(new LorentzTransform(0.5, 1000, 10, 1))
  cprint(new LorentzTransform(0.5, 1000, 20, 1))
  cprint(new LorentzTransform(0.5, 100000, 0, 1))
  cprint(new LorentzTransform(0.5, 100000, 10, 1))
  cprint(new LorentzTransform(0.5, 100000, 20, 1))
  cprint(new LorentzTransform(0.99, 100, 0, 1))
  cprint(new LorentzTransform(0.99, 100, 10, 1))
  cprint(new LorentzTransform(0.99, 100, 20, 1))
  cprint(new LorentzTransform(0.99, 1000, 0, 1))
  cprint(new LorentzTransform(0.99, 1000, 10, 1))
  cprint(new LorentzTransform(0.99, 1000, 20, 1))
  cprint(new LorentzTransform(0.99, 100000, 0, 1))
  cprint(new LorentzTransform(0.99, 100000, 10, 1))
  cprint(new LorentzTransform(0.99, 100000, 20, 1))

  //cprint(new LorentzTransform(0.99, 20.3, 0, 1))

  def cprint(lf:LorentzTransform):Unit ={
    println("=========================")
    println("Velocity = " + lf.velocity+" Xr= "+ lf.Xr+" Tr= "+lf.Tr+" c= "+lf.c+" 1 light-second")
    println("Then Gamma: "+lf.gamma+" Xl= "+ lf.Xl+" Tl= "+lf.Tl)
    println("=========================")
  }

}