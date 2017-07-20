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


}
