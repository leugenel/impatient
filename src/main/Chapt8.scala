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