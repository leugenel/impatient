package impatient

/**
  * Created by eugenel on 7/20/17.
  */
class Chapt14 {

  //Ex # 5
  //One can use lists to model trees that store values only in the leaves.
  //For example, the list ((3 8) 2 (5)) describes the tree
  def leafSum(lst: List[Any]): Int = {
    lst.foldLeft(0){ (sum, cur) => {cur match{
      case x: Int => sum+x
      case y: List[Int] => sum+leafSum(y)}}}
  }

  //Ex #6
  //A better way of modeling such trees is with case classes.
  sealed abstract class MyBinaryTree
  case class MyLeaf(value: Int) extends MyBinaryTree
  case class MyNode(left: MyBinaryTree, right: MyBinaryTree) extends MyBinaryTree
  case object Nothing extends MyBinaryTree

  def treeSum(node: MyBinaryTree):Int = {
    node match {
      case MyLeaf(x) => x
      case MyNode(n1, n2) => treeSum(n1)+treeSum(n2)
      case Nothing => 0
    }

  }

  //Ex #7
  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(node: BinaryTree*) extends BinaryTree

  def treeSum2(node: BinaryTree):Int = {
    node match {
      case Leaf(x) => x
      case Node(narr @ _*) => narr.foldLeft(0)((sum, curr) => sum + treeSum2(curr))
    }

  }

  //Ex #8
  //Extend the tree in the preceding exercise so that each nonleaf node stores an operator in addition to the child nodes.
  // Then write a function eval that computes the value.
  case class NodeOperator(op : (Int, BinaryTree) => Int, node: BinaryTree*) extends BinaryTree

  val +  = (a:Int, b:BinaryTree) => a + eval(b)
  val -  = (a:Int, b:BinaryTree) => a - eval(b)
  val *  = (a:Int, b:BinaryTree) => a * eval(b)
  val /  = (a:Int, b:BinaryTree) => if (eval(b)!=0) a/eval(b) else a

  def eval (node: BinaryTree):Int = {
    node match {
      case Leaf(x) => x
      case NodeOperator(op, narr @ _*) =>  narr.tail.foldLeft(eval(narr.head))( (sum, curr) => op(sum,curr) )
    }
  }

  //Ex #9
  //Write a function that computes the sum of the non-None values in a List[Option[Int]]
  //Don’t use a match statement
  def sumNonNone(ls:List[Option[Int]]):Int = {

    ls.foldLeft(0)( (sum ,curr) => sum + (curr match {
              case Some(a) => a
              case None => 0
           }
      )
    )
  }
  //Ex #10
  //Write a function that composes two functions of type Double => Option[Double],
  //yielding another function of the same type.
  //The composition should yield None if either function does.
  def compose ( g: Double=>Option[Double], f: Double=>Option[Double] ): Double => Option[Double] = {
    (x: Double) => f(x) match {
      case Some(a) => g(a)
      case _ => None
    }
  }

}
