package impatient

/**
  * Created by eugenel on 6/2/17.
  */
class Chapt12 {

  //#1
  //Write a function values(fun: (Int) => Int, low: Int, high: Int)
  // that yields a collection of function inputs and outputs in a given range
  val values = (fun: (Int) => Int, low: Int, high: Int) => (low to high).map(e=>(e,fun(e)))

  //#2
  //How do you get the largest element of an array with reduceLeft
  val compareus = (left:Int, right:Int)  => if(left>right)left else right
  val largestInArray = (arr:Array[Int]) => arr.reduceLeft(compareus(_,_))

  //#3
  //Implement the factorial function using to and reduceLeft, without a loop or recursion.
  val factorial = (n:Int) => (1 to n).reduceLeft(_ * _)

  //#4
  //The previous implementation needed a special case when n < 1.
  // Show how you can avoid this with foldLeft.
  val factoriali = (n:Int) => (2 to n).foldLeft(1)(_ * _)

  //#5
  //“Write a function largest(fun: (Int) => Int, inputs: Seq[Int])
  // that yields the largest value of a function within a given sequence of inputs

  val largest = (fun: (Int) => Int, inputs: Seq[Int]) => inputs.map(fun(_)).max

  //#6
  //Modify the previous function to return the input at which the output is largest
  val largestAt = (fun: (Int) => Int, inputs: Seq[Int]) => inputs.map(e =>(fun(e),e)).max._2

  //#7
  //Write a function adjustToPair that receives a function of type (Int, Int) => Int and returns the equivalent
  // function that operates on a pair. For example, adjustToPair(_ * _)((6, 7)) is 42.
  //Then use this function in conjunction with map to compute the sums of the elements in pairs
  val adjustToPair = (fun: (Int, Int) => Int, pair: (Int, Int) ) =>  fun(pair._1, pair._2)



}
