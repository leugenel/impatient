package impatient
package test.scala

import main._
import org.scalatest.FunSuite
import scala.util.{Random => ScalaRandom }

/**
  * Created by eugenel on 2/19/17.
  */
class Chapt7Test extends FunSuite {

  test(" random object verification") {
    random.setSeed(2)
    println("====OWN RANDOM===")
    println("Next Int:" + random.nextInt())
    println("Next Double:" + random.nextDouble())
    println("Next Int:" + random.nextInt())
    println("Next Double:" + random.nextDouble())
    println("====SCALA RANDOM===")
    println("Next Int:" + ScalaRandom.nextInt())
    println("Next Double:" + ScalaRandom.nextDouble())
    println("Next Int:" + ScalaRandom.nextInt())
    println("Next Double:" + ScalaRandom.nextDouble())
  }

}
