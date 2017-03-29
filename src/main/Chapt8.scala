package impatient

/**
  * Created by eugenel on 3/19/17.
  */


class BankAccount(initialBalance: Double){
    private var balance = initialBalance
    def currentBalance = balance
    def deposit(amount: Double) = {balance += amount; balance}
    def withdraw(amount: Double) = {balance -= amount; balance}
}

//“Extend the following BankAccount class to a CheckingAccount
// class that charges $1 for every deposit and withdrawal.”
class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    private val comission = 1.0
    override def deposit(amount: Double) =  super.deposit(amount-comission)
    override def withdraw(amount: Double) = super.withdraw(amount+comission)
}