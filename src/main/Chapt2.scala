package impatient
package main

import scala.collection.immutable._

/**
  * Created by eugenel on 1/8/17.
  */
object Chapt2 {
  def main(args: Array[String]): Unit = {

//    val test = new Chapt5().getme
//    println(test)
//    val test1 = new Chapt5(11).getme
//    println(test1)

//    val test = new BankAccount()
//    test.deposit(100)
//    test.deposit(1)
//    test.withdraw(99)
//    println(test.state)

//      val time1 = new Time(10, 23)
//      val time2 = new Time(10, 20)
//      println(time1.before(time2))


    //println(remNegativeButFirst(Array(1,-1,2, -4, 2,-4)).mkString(" "))
    //fileWordCountImmut("/Users/eugenel/Google Drive/Courses/ScalaLearn/1 - Getting Started/1 - Subtitles (text) for Course Introduction (2_44).txt")
    //println(lteggt(Array(1,-1,2, -4, 2,-4, 0), 0))
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

  def randArr(n:Int) : Array[Int] = {
    import scala.util.Random
    val myArra = Array.fill[Int](n)(Random.nextInt())
    myArra
  }

  //ex 3.3
  def swapArr(myArr: Array[Int]) : Array[Int] = {
    //Taken from
    //http://stackoverflow.com/questions/10158405/swapping-array-values-with-for-and-yield-scala

    // One solution
    //val myArrOut = (for( i <- 0 until(myArr.length,2); j <- (i+1).to(i,-1) if j<myArr.length ) yield myArr(j)).toArray

    //Grouped explanation
    println("Grouped")
    for (i <- myArr.grouped(2)) println(i.mkString(" "))

    //Reverse explanation
    println("Reversed")
    for (i <- myArr.grouped(2)) println(i.reverse.mkString(" "))

    //Steps
    println("Steps")
    for {b <- myArr.grouped(2); c <- b.reverse} println("b:" +b+" c: "+c)

    //Most valuable solution
    val myArrOut = (for {b <- myArr.grouped(2); c <- b.reverse} yield c ).toArray
    myArrOut
  }

  //ex 3.4
  def positiveFirst(myArr: Array[Int]):Array[Int]={
    //My solution
//    val myArrOut = new Array[Int](myArr.length)
//    val positiveIndxs = for (i <- myArr.indices if myArr(i)>0) yield i
//    val negatIndxs = for (i <- myArr.indices if myArr(i)<=0) yield i
//
//    for (i<-positiveIndxs.indices) myArrOut(i)=myArr(positiveIndxs(i))
//    for (i<-positiveIndxs.length until positiveIndxs.length+negatIndxs.length)
//          myArrOut(i)=myArr(negatIndxs(i-positiveIndxs.length))
//    myArrOut

    //Most valuable solution
    //http://stackoverflow.com/questions/32109281/produce-new-array-where-all-positive-comes-first-then-negative-or-zero-but-in-s
    myArr.filter(_ > 0) ++ myArr.filterNot(_ > 0)
  }

  //3.5
  def avgArray(myArr: Array[Double]): Double = {
    myArr.sum/myArr.length
  }

  //3.7
  def rmDuplicates (myArr: Array[Int]):Array[Int]={
    myArr.distinct
  }

  //3.9
  //you are given an array buffer of integers and want to remove all but the first negative number
  def remNegativeButFirst(myArr: Array[Int]):Array[Int]={

    //this is safe in case no negative numbers in the array
    val firstNeg = myArr.find(_ < 0) match {
      case Some(x:Int) => x
      case _  => myArr.length
    }
    val twoParts = myArr.splitAt(myArr.indexOf(firstNeg))
    (twoParts._1 :+ twoParts._2(0)) ++ twoParts._2.filter(_>0)
  }

  //4.2
  def fileWordCount (FileName : String) :Unit = {

    val wordsMap = scala.collection.mutable.Map[String, Int]().withDefaultValue(0)

    val in = new java.util.Scanner(new java.io.File(FileName))

    while (in.hasNext()) wordsMap(in.next())+=1

    for ( (k,v) <- wordsMap) println("Word:" + k + " "+v+" times")

  }

  //4.3 and 4.4
  def fileWordCountImmut (FileName : String) :Unit = {

    var wordsMap = Map[String, Int]().withDefaultValue(0)

    val in = new java.util.Scanner(new java.io.File(FileName))

    var word:String=""

    while (in.hasNext()) {
      word = in.next()
      wordsMap += (word -> (wordsMap(word)+1))
    }

    for ( (k,v) <- wordsMap) println("Word:" + k + " "+v+" times")
    println("======Sorted:==========")
    for ( (k,v) <- ListMap(wordsMap.toSeq.sortWith(_._2 >_._2):_*)) println("Word:" + k + " "+v+" times")

  }

  //4.8
  def minmax (values: Array[Int]): Tuple2[Int, Int] = {
    (values.max, values.min)
  }

  //4.9
  def lteggt (values: Array[Int], v:Int): Tuple3[Int, Int, Int] = {
    (values.count(_<v), values.count(_==v), values.count(_>v))
  }

}

