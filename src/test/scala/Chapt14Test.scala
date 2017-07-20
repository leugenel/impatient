package  impatient

import org.scalatest.FunSuite

/**
  * Created by eugenel on 7/20/17.
  */
class Chapt14Test extends FunSuite{


  test ("Leaf sum"){
    assert(new Chapt14().leafSum(List(List(3,8), 2, List(5, List(1,2)))) == 21)
    assert(new Chapt14().leafSum(List(List(3,8), 2, List(5, List(1,List(1,1))))) == 21)
    assert(new Chapt14().leafSum(List(1, 2, 3)) == 6)
    assert(new Chapt14().leafSum(List()) == 0)
  }


  test ("Tree sum") {
    val chapt14 = new Chapt14()
    val one = chapt14.MyLeaf(1)
    val two = chapt14.MyLeaf(2)
    val five = chapt14.MyLeaf(5)
    val nodeFive = chapt14.MyNode(five, chapt14.Nothing)
    val nodeOne = chapt14.MyNode(one, chapt14.Nothing)
    val nodeTwo = chapt14.MyNode(two, nodeFive)
    val nodeRoot = chapt14.MyNode(nodeOne, nodeTwo)

    assert(chapt14.treeSum(nodeRoot) == 8)
  }

}
