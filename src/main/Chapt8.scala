package impatient

/**
  * Created by eugenel on 3/19/17.
  */


class BankAccount(initialBalance: Double){
    private var balance = initialBalance
    def currentBalance: Double = balance
    def deposit(amount: Double):Double = {balance += amount; balance}
    def withdraw(amount: Double):Double = {balance -= amount; balance}
}

//8.1
//“Extend the following BankAccount class to a CheckingAccount
// class that charges $1 for every deposit and withdrawal.”
class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    private val comission = 1.0
    override def deposit(amount: Double):Double =  super.deposit(amount-comission)
    override def withdraw(amount: Double):Double = super.withdraw(amount+comission)
}

//8.2
//“Extend the BankAccount class of the preceding exercise into a class SavingsAccount that earns interest every month
// (when a method earnMonthlyInterest is called) and has three free deposits or withdrawals every month.
// Reset the transaction count in the earnMonthlyInterest method.”
class SavingsAccount (initialBalance: Double, interest: Double) extends BankAccount(initialBalance) {
    private val freeCount = 3
    private val comission = 1.0
    private var transactionCount = 0

    override def deposit(amount: Double):Double =  {
        transactionCount+=1
        if(transactionCount<=freeCount)  super.deposit(amount)
        else super.deposit(amount-comission)
    }

    override def withdraw(amount: Double):Double = {
        transactionCount+=1
        if(transactionCount<=freeCount) super.withdraw(amount)
        else super.withdraw(amount+comission)
    }

    def earnMonthlyInterest:Double = {
        transactionCount = 0
        super.deposit(currentBalance*interest)
    }
}

//8.4
//“Define an abstract class Item with methods price and description.
// A SimpleItem is an item whose price and description are specified in the constructor.
// Take advantage of the fact that a val can override a def. A Bundle is an item that contains other items.
// Its price is the sum of the prices in the bundle.
// Also provide a mechanism for adding items to the bundle and a suitable description method.”

abstract class Item{
    def price: Double
    def description: String
}

class SimpleItem(override val price: Double, override val description: String) extends Item

class Bundle (items: Array[Item]) extends Item {
    private var bundle: Array[Item] = items

    override def price:Double = {
        var bundlePrice: Double = 0
        for (item <- bundle){
            bundlePrice+= item.price
        }
        bundlePrice
    }

    override def description: String = {
        var outputString:String = "The bundle contains: \n"
        for (item <- bundle){
            outputString+= item.description + "\n"
        }
        outputString+="The bundle price is: "+price.toString
        outputString
    }

    def addItemtoBundle(item: SimpleItem) :Unit = {
        bundle=bundle:+item
    }

}

//8.5
//“Design a class Point whose x and y coordinate values can be provided in a constructor.
// Provide a subclass LabeledPoint whose constructor takes a label value and x and y coordinates, such as”
//“new LabeledPoint("Black Thursday", 1929, 230.07)”
class Point (_x:Int, _y:Int){
    val x:Int = _x
    val y:Int = _y
}
class LabeledPoint(_label: String, x:Int, y:Int) extends Point(x, y){
    val label:String = _label
}

//8.6
//“Define an abstract class Shape with an abstract method centerPoint and subclasses Rectangle and Circle.
// Provide appropriate constructors for the subclasses and override the centerPoint method in each subclass.”

abstract class Shape {
    def centerPoint(): Point
}

class Rectangle (p1:Point, p2:Point) extends Shape{
    override def centerPoint(): Point ={
        new Point ( (p1.x+p2.x)/2, (p1.y+p2.y)/2)
    }
}

class Circle (override val centerPoint:Point, var radius:Int) extends Shape


//8.7
//“Provide a class Square that extends java.awt.Rectangle and has three constructors: one that constructs a square with
// a given corner point and width, one that constructs a square with corner (0, 0)
// and a given width, and one that constructs a square with corner (0, 0) and width 0.”
class Square(width:Int, x:Int, y:Int) extends java.awt.Rectangle(x, y, width, width){
    def this(width:Int) = this(width,0,0)
    def this() = this(0,0,0)
}

//8.9
//“replace val range with a def. What happens when you also use a def in the Ant subclass?
// What happens when you use a val in the subclass? Why?”
class Creature {
    def range: Int = 10
    val env: Array[Int] = new Array[Int](range)
}
class Ant extends Creature {
    override val range = 2
}


