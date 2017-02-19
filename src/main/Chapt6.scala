package main


/**
  * Created by eugenel on 2/6/17.
  */
object Chapt6 extends App {

   //Added program argument -Dscala.time that print the total time
   println("How we can launch w/o main method")

}


//6.1
//object myUnits extends Enumeration{
//  type myUnits = Value
//  val inch, centimeter, gallon, liter, mile, kilometr = Value
//}

class UnitConversions {
  def Conversion (value :Double) :Double = {
    if (value <= 0) throw new Exception("The value should be positive")
    0
  }
}

object inchesToCentimeters extends UnitConversions{
  override def Conversion(value: Double) :Double= {
    super.Conversion(value)
    value*2.54
  }
}

object gallonsToLiters extends UnitConversions{
  override def Conversion(value: Double) :Double= {
    super.Conversion(value)
    value*3.78541
  }
}

object milesToKilometers extends UnitConversions{
  override def Conversion(value: Double) :Double = {
    super.Conversion(value)
    value*1.60934
  }
}

//6.4
class Point (var x:Int, var y:Int){}
object Point {
  def apply (x:Int, y:Int) = new Point(x,y)
}

//6.5
object ArgReverse extends App {
  if (args.length>0) for (a <- args.reverse ) println(a)
  else println ("No arguments found")
}

//6.7
//clubs (♣), diamonds ( ♦ ), hearts (♥) and spades (♠)
object CardSuits extends Enumeration{
  type CardSuits = Value
  val clubs = Value(0, "♣")
  val diamonds = Value(1, "♦")
  val hearts = Value(2, "♥")
  val spades = Value(3, "♠")

  def getColor(invalue: Value) : String = {
    if (invalue == clubs) "B"
    else if(invalue == diamonds) "R"
    else if(invalue == hearts) "R"
    else "B"
  }

  //Solution from http://stackoverflow.com/questions/4346580/how-to-add-a-method-to-enumeration-in-scala/4346708#4346708
  class SuitValue(suit: Value){
    def isBlack : Boolean = {
      suit match {
        //See backquotes explanation
        //http://stackoverflow.com/questions/7905023/in-scala-pattern-matching-what-is-suspicious-shadowing-by-a-variable-pattern?noredirect=1&lq=1
        case `clubs` => true
        case `spades` => true
        case _ => false
      }
    }
    def isRed:Boolean = !isBlack
  }
  implicit def value2SuitValue(suit: Value) : SuitValue = new SuitValue(suit)
  ////

}