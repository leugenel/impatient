package impatient
package test.scala

import org.scalatest.FunSuite

/**
  * Created by eugenel on 3/29/17.
  */
class Chapt8Test extends FunSuite{
  test("CheckingAccount class :Check deposit and withdraw with commissions") {
    var cha:CheckingAccount = new CheckingAccount(0)
    cha.deposit(10)
    assert(cha.currentBalance==9.0)
    cha.withdraw(5)
    assert(cha.currentBalance==3.0)
  }

  test ("SavingsAccount class"){
    var cha:SavingsAccount = new SavingsAccount(0, 0.1)
    //#1
    cha.deposit(10)
    assert(cha.currentBalance==10)
    //#2
    cha.withdraw(5)
    assert(cha.currentBalance==5)
    //#3
    cha.deposit(20)
    assert(cha.currentBalance==25)
    //#4 Now they take comission
    cha.deposit(5)
    assert(cha.currentBalance==29)
    //#5
    cha.deposit(6)
    //Now they take comission again
    assert(cha.currentBalance==34)
    //Interest
    cha.earnMonthlyInterest
    assert(cha.currentBalance==37.4)
    //#1
    cha.withdraw(7.4)
    assert(cha.currentBalance==30)
    //#2
    cha.withdraw(1)
    assert(cha.currentBalance==29)
    //#3
    cha.withdraw(1)
    assert(cha.currentBalance==28)
    //#4
    cha.withdraw(1)
    assert(cha.currentBalance==26)
    //Interest
    cha.earnMonthlyInterest
    assert(cha.currentBalance==28.6)

  }

  test ("Class Bundle"){
    val item1 = new SimpleItem(10, "SimpleItem #1")
    val item2 = new SimpleItem(20, "SimpleItem #2")
    assert(item1.description=="SimpleItem #1")
    assert(item2.description=="SimpleItem #2")
    assert(item1.price==10)
    assert(item2.price==20)
    var bundle = new Bundle(Array(item1, item2))
    assert(bundle.price==30)
    val item3= new SimpleItem(30, "SimpleItem #3")
    bundle.addItemtoBundle(item3)
    assert(bundle.price==60)
    println(bundle.description)
  }

  test("class LabeledPoint"){
    val p1 = new LabeledPoint("label1", 1,2)
    val p2 = new LabeledPoint("label2", 10,20)
    assert(p1.label=="label1")
    assert(p1.x==1)
    assert(p1.y==2)
    assert(p2.label=="label2")
    assert(p2.x==10)
    assert(p2.y==20)

  }

  test ("class Shape"){
    //Rectangle
    val p1:Point = new Point(10, 20)
    val p2:Point = new Point(6, 30)
    val rect:Rectangle = new Rectangle(p1, p2)
    val rectCenter = rect.centerPoint()
    assert(rectCenter.x==8)
    assert(rectCenter.y==25)
    val circle:Circle  = new Circle(p1, 1)
    assert(circle.centerPoint.x==10)
    assert(circle.centerPoint.y==20)
  }

  test ("class Square"){
    val sq: Square = new Square(5,10,20)
    assert(sq.getHeight==5)
    assert(sq.getWidth==5)
    assert(sq.getX==10)
    assert(sq.getY==20)
    val sqz:Square = new Square(50)
    assert(sqz.getHeight==50)
    assert(sqz.getWidth==50)
    assert(sqz.getX==0)
    assert(sqz.getY==0)
    val sqzz:Square = new Square()
    assert(sqzz.getHeight==0)
    assert(sqzz.getWidth==0)
    assert(sqzz.getX==0)
    assert(sqzz.getY==0)

  }
  test ("class Creature"){
    val ant:Ant = new Ant()
    assert(ant.range==2)
  }

}
