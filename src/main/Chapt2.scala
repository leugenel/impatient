package main

import scala.collection.immutable.StringOps

/**
  * Created by eugenel on 1/8/17.
  */
object Chapt2 {
  def main(args: Array[String]): Unit = {
    println(str_multipl_unicode_noloop("Hello"))
  }

  //The signum of a number is 1 if the number is positive, –1 if it is negative, and 0 if it is zero. Write a function that computes this value
  def signum (x : Int) = if (x > 0) 1 else if (x == 0) 0 else -1

  def countdown = for (i <- 0 to 10) println(10-i)

  def countdown (n:Int) = for (i <- 0 to n) println(n-i)

  def str_multipl_unicode (str: String ) = {
    var mltp: Long = 1
    for (i <- str) mltp *= i.toInt
    mltp
  }
  def str_multipl_unicode_noloop (str: StringOps ) = {
    var mltp: Long = 1
    str.foreach(i => mltp *= i.toInt)
    mltp
  }


}
