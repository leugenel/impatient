package  impatient

import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

/**
  * Created by eugenel on 7/3/17.
  */
class Chapt13Test extends FunSuite {

  test("Char map"){
    assert(new Chapt13().indexes("Missisipi") ==
          Map('M' -> Set(0), 'i' -> Set(1, 4, 6, 8), 's' -> Set(2, 3, 5), 'p' -> Set(7)))
    assert(new Chapt13().indexes("AbraCadabraCa") ==
          Map('A' -> Set(0), 'a' -> Set(5, 10, 12, 7, 3), 'b' -> Set(1, 8), 'C' -> Set(4, 11), 'r' -> Set(2, 9), 'd' -> Set(6)))
    assert(new Chapt13().indexes("") == Map())

  }


  test ("Remove elements"){
    assert(new Chapt13().rmFromList(ListBuffer(0,1,2,3,4,5,6,7,8,9,10,11)) == ListBuffer(0, 2, 4, 6, 8, 10))
    assert(new Chapt13().rmFromList(ListBuffer(0,1,2,3,4,5,6,7,8,9,10)) == ListBuffer(0, 2, 4, 6, 8, 10))
    assert(new Chapt13().rmFromList(ListBuffer(0,1)) == ListBuffer(0))
    assert(new Chapt13().rmFromList(ListBuffer(0)) == ListBuffer(0))
    assert(new Chapt13().rmFromList(ListBuffer()) == ListBuffer())
  }

  test ("Remove elements 2 other list"){
    assert(new Chapt13().rmFromList2Other(ListBuffer(0,1,2,3,4,5,6,7,8,9,10,11)) == ListBuffer(0, 2, 4, 6, 8, 10))
    assert(new Chapt13().rmFromList2Other(ListBuffer(0,1,2,3,4,5,6,7,8,9,10)) == ListBuffer(0, 2, 4, 6, 8, 10))
    assert(new Chapt13().rmFromList2Other(ListBuffer(0,1)) == ListBuffer(0))
    assert(new Chapt13().rmFromList2Other(ListBuffer(0)) == ListBuffer(0))
    assert(new Chapt13().rmFromList2Other(ListBuffer()) == ListBuffer())
  }

  test ("Proper Array") {
    assert(new Chapt13().howProperNames(Array("Tom", "Fred", "Harry"), Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)) == List('3', '5'))
    assert(new Chapt13().howProperNames(Array("ATom", "AFred", "AHarry"), Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)) == List())
    assert(new Chapt13().howProperNames(Array("Tom", "Fred", "Harry"), Map("Tom" -> 3, "Fred" -> 4, "Harry" -> 5)) == List('3', '4','5'))
    assert(new Chapt13().howProperNames(Array(), Map("Tom" -> 3, "Fred" -> 4, "Harry" -> 5)) == List())
    assert(new Chapt13().howProperNames(Array("Tom", "Fred", "Harry"), Map()) == List())
  }

  test("mkstring"){
    assert(new Chapt13().likemkString(List("a", "b", "c"), ":") == "a:b:c")
    assert(new Chapt13().likemkString(List("a", "b", "c"), "") == "abc")
    assert(new Chapt13().likemkString(List("a"), ":") == "a")
  }

  test("zip lists"){
    assert(new Chapt13().zipLists(List(33.0, 2.0, 7.0), List(3, 4, 5)) == List(99.0, 8.0, 35.0))
    assert(new Chapt13().zipLists(List(33.0, 2.0, 7.0), List(3)) == List(99.0))
    assert(new Chapt13().zipLists(List(33.0, 2.0, 7.0), List()) == List())
    assert(new Chapt13().zipLists(List(), List(3)) == List())
    assert(new Chapt13().zipLists(List(), List()) == List())
  }

   test ("two dimension array"){
     assert(  runtime.ScalaRunTime.stringOf(new Chapt13().twoDimArray(Array(33.0, 2.0, 7.0, 4.0, 1.0, 9.0), 3)) ==  "Array(Array(33.0, 2.0, 7.0), Array(4.0, 1.0, 9.0))"  )
     assert(  runtime.ScalaRunTime.stringOf(new Chapt13().twoDimArray(Array(33.0, 2.0, 7.0), 1)) ==  "Array(Array(33.0), Array(2.0), Array(7.0))" )
     assert(  runtime.ScalaRunTime.stringOf(new Chapt13().twoDimArray(Array(33.0, 2.0, 7.0, 4.0, 1.0, 9.0, 1.0, 44.0), 4)) ==  "Array(Array(33.0, 2.0, 7.0, 4.0), Array(1.0, 9.0, 1.0, 44.0))" )
     assert(  runtime.ScalaRunTime.stringOf(new Chapt13().twoDimArray(Array(33.0), 1)) ==  "Array(Array(33.0))" )
   }

}
