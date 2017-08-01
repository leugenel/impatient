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

  test ("Tree sum #2"){
    val chapt14 = new Chapt14()
    val problemNode = chapt14.Node(chapt14.Node(chapt14.Leaf(3), chapt14.Leaf(8)), chapt14.Leaf(2), chapt14.Node(chapt14.Leaf(5)))
    assert(chapt14.treeSum2(problemNode) == 18)
    assert(chapt14.treeSum2(chapt14.Leaf(2)) == 2)
    assert(chapt14.treeSum2(chapt14.Node(chapt14.Node(chapt14.Leaf(0)))) == 0)
    val problemNode2 = chapt14.Node(chapt14.Node(chapt14.Node(chapt14.Leaf(3), chapt14.Leaf(8))), chapt14.Node(chapt14.Leaf(2)), chapt14.Node(chapt14.Leaf(5)), chapt14.Leaf(1))
    assert(chapt14.treeSum2(problemNode2) == 19)

  }

  test ("Tree sum ex#8"){
    val chapt14 = new Chapt14()
    val problemNode = chapt14.NodeOperator(chapt14.+, chapt14.NodeOperator(chapt14.*, chapt14.Leaf(3), chapt14.Leaf(8)), chapt14.Leaf(2),
                                          chapt14.NodeOperator(chapt14.-, chapt14.Leaf(0), chapt14.Leaf(5)) )

    assert(chapt14.eval( chapt14.NodeOperator( chapt14./ , chapt14.Leaf(5), chapt14.Leaf(2)) )  == 2 )
    assert(chapt14.eval( chapt14.NodeOperator(chapt14.+, chapt14.NodeOperator(chapt14.*, chapt14.Leaf(3), chapt14.Leaf(8)),
            chapt14.NodeOperator(chapt14.-, chapt14.Leaf(0), chapt14.Leaf(5)) ) ) == 19 )
    assert(chapt14.eval(problemNode) == 21)
    assert(chapt14.eval(chapt14.NodeOperator(chapt14.-, chapt14.Leaf(0))) == 0)
    assert(chapt14.eval(chapt14.NodeOperator(chapt14./, chapt14.Leaf(1), chapt14.Leaf(0) ) ) == 1)

  }

  test ("Sum non None"){
    val chapt14 = new Chapt14()
    assert(chapt14.sumNonNone(List( Option(1), None, Option(1))) == 2)
    assert(chapt14.sumNonNone(List( Option(1), None, Option(1), None, Some(32))) == 34)
    assert(chapt14.sumNonNone(List(None)) == 0)
  }

  test ("Function from Fuction"){
    val chapt14 = new Chapt14()
    def f(x: Double) = if (x != 1) Some(1 / (x - 1)) else None
    def g(x: Double) = if (x >= 0) Some(math.sqrt(x)) else None
    val h = chapt14.compose(g, f)
    assert(h(2.0) == Some(1.0))
    assert(h(1.0) == None)
    assert(h(0) == None)
    assert(h(5)==Some(0.5))
  }


}
