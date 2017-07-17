package impatient

import scala.collection.mutable.ListBuffer

/**
  * Created by eugenel on 6/22/17.
  */
class Chapt13 {

  //#1
  //Write a function that, given a string, produces a map of the indexes of all characters.
  // For example, indexes("Mississippi") should return a map associating 'M' with the set {0},
  // 'i' with the set {1, 4, 7, 10}, and so on. Use a mutable map of characters to mutable sets.
  // How can you ensure that the set is sorted?

  def indexes (mystr:String) = {

    mystr.indices.foldLeft( Map[Char, Set[Int]]() ) {
      (c,t) => c + ( mystr(t) ->  (c.getOrElse(mystr(t), Set[Int]()) + t)  )
    }
  }

  //#3
  //Write a function that removes every second element from a ListBuffer. Try it two ways.
  // Call remove(i) for all even i starting at the end of the list.
  // Copy every second element to a new list. Compare the performance.

   def rmFromList(mylb: ListBuffer[Int]) : ListBuffer[Int] = {
     //mylb.indices.foldRight(){(a,b)=>{ if(a%2!=0){mylb.remove(a)} } }//took 3 ms
     mylb.indices.reverse.foreach{(i:Int)=> if( i%2!=0 && i<mylb.length ) mylb.remove(i)} //took 2ms
     mylb
   } //took 2 ms
  def rmFromList2Other(mylb: ListBuffer[Int]) : ListBuffer[Int] = {
    var mylbnew = ListBuffer[Int]()
    mylb.indices.foreach{(i:Int)=> if( i%2==0 && i<mylb.length) mylbnew+=mylb(i)}
    mylbnew
  }//took 1 ms

  //#4
  // Write a function that receives a collection of strings and a map from strings to integers.
  // Return a collection of integers that are values of the map corresponding to one of the strings in the collection.
  // For example, given Array("Tom", "Fred", "Harry") and Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5), return Array(3, 5).
  // Hint: Use flatMap to combine the Option values returned by get.

   def howProperNames(myarray:Array[String], mymap: Map[String, Int]) = {
     mymap.flatMap(s =>if(myarray contains s._1){s._2.toString;}
                  else "" )
   }

  //#5
  //Implement a function that works just like mkString, using reduceLeft.
  def likemkString(l: List[String] , sep: String):String = l.reduceLeft{(a,b) => a+sep+b}

  //#7
  //In SeDction 13.10, “Zipping,” on page 187, the expression (prices zip quantities) map { p => p._1 * p._2 }
  // is a bit inelegant. We can’t do (prices zip quantities) map { _ * _ } because _ * _ is a function with two arguments,
  // and we need a function with one argument that is a tuple.
  // The tupled method of the Function object changes a function with two arguments to one that takes a tuple.
  // Apply tupled to the multiplication function so you can map it over the list of pairs.
  def zipLists(l1: List[Double], l2: List[Int]) :List[Double] = {
    val multiply = ((a:Double,b:Int ) => a * b).tupled
    (l1 zip l2) map(multiply (_))
  }

  //#8
  //Write a function that turns an array of Double values into a two-dimensional array.
  // Pass the number of columns as a parameter. For example, with Array(1, 2, 3, 4, 5, 6) and three columns,
  // return Array(Array(1, 2, 3), Array(4, 5, 6)). Use the grouped method.

   def twoDimArray (arr:Array[Double], cnumber:Int) = arr.grouped(cnumber).toArray


}

