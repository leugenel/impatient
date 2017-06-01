package impatient

import java.nio.file.Path
import scala.language.dynamics

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

//#7
//Implement a class BitSequence that stores a sequence of 64 bits packed in a Long value.
// Supply apply and update operators to get and set an individual bit.

class BitSequence(inpacked:Long) {

  var packed:Long = inpacked

  override def toString: String = {

    //Old fashion code
    //    var i = 0
    //    var str : String = ""
    //    while( (packed >> i) > 0 ){
    //      str += (packed >> i) & 1
    //      i+=1
    //    }
    //    str.reverse

    //Modern :)
    val str = (for {i <- 0 until 64 if (packed >> i) > 0 } yield (packed >> i) & 1).mkString
    if (str.length==0) "0" else str.reverse
  }

  def apply(pos: Int): Int = {
    ((packed >> pos) & 1).toInt
  }

  def update(pos: Int, value:Int): Unit = {
    if (value!=0 )  packed = packed | (1 << pos)
    else packed = packed & ~(1 << pos)
  }
}

//#9
//Define an object PathComponents with an unapply operation class that extracts the directory
// path and file name from an java.nio.file.Path

class PathComponents (mypath:java.nio.file.Path){
  val mp:java.nio.file.Path = mypath
}

object PathComponents {
  def unapply(pc: PathComponents): Option[(Path, Path)]= {
    if (pc.mp.getFileName == null) None
    if (pc.mp.getParent == null) None
    Some(pc.mp.getParent, pc.mp.getFileName)
  }
}

//#10
//Modify the PathComponents object of the preceding exercise to instead define an unapplySeq operation
// that extracts all path segments. For example, for the file /home/cay/readme.txt, you should produce
// a sequence of three segments: home, cay, and readme.txt.

class PathComponentsSeq (mypath:java.nio.file.Path){
  val mp:java.nio.file.Path = mypath
}

object PathComponentsSeq {
  def unapplySeq(pc: PathComponentsSeq): Option[Seq[Path]]= {
    var parent = pc.mp.iterator()
    if (!parent.hasNext) None
    else {
      var lp = Seq(parent.next())
      while(parent.hasNext) lp = lp :+ parent.next()
      Some(lp)
    }
  }
}

//#11
//Improve the dynamic property selector in Section 11.11, “Dynamic Invocation,” on page 150 so that one doesn’t have
// to use underscores. For example, sysProps.java.home should select the property with key "java.home".
// Use a helper class, also extending Dynamic, that contains partially completed paths.
//The idea taken  from http://codegist.net/search/chapter%20scala/7
class DynamicProps(props: java.util.Properties) extends Dynamic {

  def updateDynamic(name: String)(value: String): DPropHelper = {
    new DPropHelper(name, props)
  }

  def selectDynamic(name:String): DPropHelper = {
    new DPropHelper(name, props)
  }
}

class DPropHelper(name: String,  props: java.util.Properties) extends Dynamic {

  def updateDynamic(name: String)(value: String): Unit = {
    props.setProperty(this.name + "." + name, value)
  }

  def selectDynamic(name:String): DPropHelper = {
    new DPropHelper(this.name + "." + name, props)
  }

  override def toString: String = props.getProperty(name)
}





