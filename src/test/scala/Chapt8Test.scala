package impatient
package test.scala

import org.scalatest.FunSuite

/**
  * Created by eugenel on 3/29/17.
  */
class Chapt8Test extends FunSuite{
  test("Check deposit and withdraw with commissions") {
    var cha:CheckingAccount = new CheckingAccount(0)
    cha.deposit(10)
    assert(cha.currentBalance==9.0)
    cha.withdraw(5)
    assert(cha.currentBalance==3.0)
  }
}
