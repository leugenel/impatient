package  impatient

import org.scalatest.FunSuite
/**
  * Created by eugenel on 6/2/17.
  */
class Chapt12Test extends FunSuite{

  test ("values"){
    assert(new Chapt12().values(x=>x*x, -5,5)==
                          Vector((-5,25), (-4,16), (-3,9), (-2,4), (-1,1), (0,0), (1,1), (2,4), (3,9), (4,16), (5,25)))

    assert(new Chapt12().values(x=>math.sqrt(x).toInt, 1,10)==
                          Vector((1,1), (2,1), (3,1), (4,2), (5,2), (6,2), (7,2), (8,2), (9,3), (10,3)))

    val arr = Array(1,12,3,5,0)
    assert(new Chapt12().largestInArray(arr)==12)
  }

  test ("factorial"){
    assert(new Chapt12().factorial(5)==120)
    assert(new Chapt12().factorial(1)==1)
    assert(new Chapt12().factoriali(5)==120)
    assert(new Chapt12().factoriali(0)==1)
    assert(new Chapt12().factoriali(1)==1)
  }

  test ("largest"){
    assert(new Chapt12().largest(x=>10*x-x*x, 1 to 10)==25)
    assert(new Chapt12().largest(x=>10*x-x*x*x, 1 to 10)==12)

    assert(new Chapt12().largestAt(x=>10*x-x*x, 1 to 10)==5)
    //Vector((9,1), (12,2), (3,3), (-24,4), (-75,5), (-156,6), (-273,7), (-432,8), (-639,9), (-900,10))
    assert(new Chapt12().largestAt(x=>10*x-x*x*x, 1 to 10)==2)
  }

  test ("adjustToPair"){
    assert(new Chapt12().adjustToPair(_ * _, (6,7))==42)
    assert(new Chapt12().adjustToPair(_ + _, (6,7))==13)

  }

  test ("correspond"){
    //#8
    //Make a call to corresponds that checks whether the elements
    //in an array of strings have the lengths given in an array of integers.
    val sarr : Array[String] = Array("one", "tw", "y")
    val lngth : Array[Int] = Array(3,2,1)
    val badarr : Array[Int] = Array(3,2,6)
    assert(sarr.corresponds(lngth)(_.length == _))
    assert(!sarr.corresponds(badarr)(_.length == _))
  }

}
