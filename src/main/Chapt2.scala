package main

import scala.collection.immutable.StringOps

/**
  * Created by eugenel on 1/8/17.
  */
object Chapt2 {
  def main(args: Array[String]): Unit = {
    println(inpower(8,-1))
  }

  //The signum of a number is 1 if the number is positive, –1 if it is negative, and 0 if it is zero. Write a function that computes this value
  def signum (x : Int) : Int = if (x > 0) 1 else if (x == 0) 0 else -1

  def countdown() : Unit = for (i <- 0 to 10) println(10-i)

  def countdown (n:Int) : Unit = for (i <- 0 to n) println(n-i)

  def str_multipl_unicode (str: String ) : Long = {
    var mltp: Long = 1
    for (i <- str) mltp *= i.toInt
    mltp
  }
  def str_multipl_unicode_noloop (str: StringOps ) : Long = {
    var mltp: Long = 1
    str.foreach(i => mltp *= i.toInt)
    mltp
  }

  def str_multipl_unicode_recurs (str: String ) : Long = {
    if (str.length == 0) return 1
    str.head * str_multipl_unicode_recurs(str.tail)
  }

  def inpower (x: Double, n: Int) : Double = {
    if (n<0) 1/inpower(x,-n)
    else if (n%2==0 && n>0) inpower(x, n/2)*inpower(x, n/2)
    else if (n%2!=0 && n>0) x*inpower(x, n-1)
    else 1
  }

}
