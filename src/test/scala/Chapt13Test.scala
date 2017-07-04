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

}
