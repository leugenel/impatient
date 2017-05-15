package impatient

import java.io.IOException

import scala.util.control.Exception

/**
  * Created by eugenel on 5/8/17.
  */
class Chapt11 {

}

//#3
//Implement the Fraction class with operations + - * /.
// Normalize fractions, for example, turning 15/–6 into –5/2. Divide by the greatest common divisor
class Fraction(n:Int,d:Int) {
  private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d)
  private val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d)

  override def toString = {
    if (den == 0) "not defined"
    else if (num == 0 || den == 1) s"$num"
    else s"$num/$den"
  }

  def sign(a: Int): Int = if (a > 0) 1 else if (a < 0) -1 else 0

  def gcd(a: Int, b: Int): Int = if (b == 0) Math.abs(a) else gcd(b, a % b)

  def +(other: Fraction): Fraction = {
    new Fraction(num * other.den + other.num * den, den * other.den)
  }

  def -(other: Fraction): Fraction = {
    new Fraction(num * other.den - other.num * den, den * other.den)
  }

  def *(other: Fraction): Fraction = {
    new Fraction(num * other.num, den * other.den)
  }

  def /(other: Fraction): Fraction = {
    new Fraction(num * other.den, den * other.num)
  }
}

//#4
//Implement a class Money with fields for dollars and cents.
// Supply +, - operators as well as comparison operators == and <
class Money (d:Int, c:Int) {
  private var dollar = d
  private var cent  = c

  def + (other:Money) :Money ={
    new Money(dollar + other.dollar, cent + other.cent)
  }

  def - (other:Money):Money ={
    new Money(if (dollar - other.dollar > 0) dollar - other.dollar else 0 , if (cent - other.cent > 0) cent - other.cent else 0)
  }

  def == (other: Money):Boolean = {
    dollar*100 + cent == other.dollar*100 + other.cent
  }

  def < (other: Money):Boolean = {
    dollar*100 + cent < other.dollar*100 + other.cent
  }

  def > (other: Money):Boolean = {
    dollar*100 + cent > other.dollar*100 + other.cent
  }
}

//#5
//Provide operators that construct an HTML table. For example,
//Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"
//should produce
// <table><tr><td>Java</td><td>Scala</td></tr><tr><td>Gostling</td><td>Odersky</td> </tr><tr><td>JVM</td><td>JVM, .NET</td></tr></table>

class HTMLTableBuilder (table:String = "") {
  val tdTag :String = "<td>"
  val trTag :String = "<tr>"
  val endtdTag:String = "</td>"
  val endtrTag:String = "</tr>"
  val endTableIndex :Int = table.indexOf("</tr></table>")
  val (start, end ) = if(endTableIndex == -1) ("", "") else table.splitAt(endTableIndex)

  def Table():HTMLTableBuilder ={
    new HTMLTableBuilder("<table><tr></tr></table>")
  }

  def | (cell:String):HTMLTableBuilder = {
    if (cell.length==0) new HTMLTableBuilder(start+end)
    else new HTMLTableBuilder(start+tdTag+cell+endtdTag+end)
  }

  def || (cell:String):HTMLTableBuilder ={
    //We start the new row and then add the cell
    if (cell.length==0) new HTMLTableBuilder(start+end)
    else new HTMLTableBuilder(start+endtrTag+trTag+end) | cell
  }

  override def toString: String = {
    table
  }

}