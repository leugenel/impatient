package impatient
package main

import scala.math._

/**
  * Created by eugenel on 2/19/17.
  */

//7.3
package object random {

    val A = 1664525
    val B = 1013904223
    val N = 32
    var PreviousInt :Int = 0
    var PreviousDouble :Double = 0

    def setSeed(seed:Int) : Unit = {
      PreviousInt = seed
      PreviousDouble = seed
    }
    //Scala assignment returns Unit so we need the additional line to return the expected value
    //http://stackoverflow.com/questions/14797547/incrementing-and-getting-value?noredirect=1&lq=1
    def nextInt():Int = {
      PreviousInt=((PreviousInt*A + B)%pow(2, N)).toInt
      PreviousInt
    }

    def nextDouble() : Double = {
      PreviousDouble=(PreviousDouble*A + B)%pow(2, N)
      PreviousDouble
    }

}

object userSecret extends App{

  import java.lang.System

  val username = System.getProperty("user.name");
  println("username = " + username);
  val pwd = System.console()
  if(pwd!=null) {
    val pwdpwd = pwd.readPassword()
    println ("Password = " + pwdpwd)
  }
  else
    println("Console object is null");


}