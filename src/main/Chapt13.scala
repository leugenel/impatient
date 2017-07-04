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
     mylb.indices.foldRight(){(a,b)=>{ if(a%2!=0){mylb.remove(a)} } }
     mylb
   }

}

