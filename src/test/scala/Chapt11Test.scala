package impatient

import org.scalatest.FunSuite

/**
  * Created by eugenel on 5/8/17.
  */
class Chapt11Test extends FunSuite {

  test("Fraction"){
    val half: Fraction  = new Fraction(1,2)
    val third: Fraction = new Fraction(1,3)
    assert("1/6"==(half*third).toString )
    assert("3/2"==(half/third).toString )
    assert ("5/6"==(half+third).toString)
    assert ("1/6"==(half-third).toString)

    val mhalf : Fraction  = new Fraction(2,4)
    val mthird: Fraction = new Fraction(2,6)
    assert("1/6"==(mhalf*mthird).toString )
    assert("3/2"==(mhalf/mthird).toString )
    assert ("5/6"==(mhalf+mthird).toString)
    assert ("1/6"==(mhalf-mthird).toString)

    val mzero : Fraction  = new Fraction(0,4)
    val minone: Fraction = new Fraction(-1,1)
    assert("0"==(mzero*minone).toString )
    assert("0"==(mzero/minone).toString )
    assert ("-1"==(mzero+minone).toString)
    assert ("1"==(mzero-minone).toString)

  }

  test ("Money") {
    val mon1: Money = new Money(10, 50)
    val mon2: Money = new Money(6, 10)
    val mondiff:Money = new Money (4, 40)
    assert(mon1>mon2)
    assert(mon2<mon1)
    assert(mon1 == (mondiff+mon2))
    assert(mon2 == (mon1-mondiff))

  }

  test ("HTML Table builder"){

    //The book string
    assert(  ((new HTMLTableBuilder).Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET").toString ==
      "<table><tr><td>Java</td><td>Scala</td></tr><tr><td>Gosling</td><td>Odersky</td></tr><tr><td>JVM</td><td>JVM, .NET</td></tr></table>")

    //Empty string
    assert( ((new HTMLTableBuilder).Table() | "" |"Java").toString == "<table><tr><td>Java</td></tr></table>" )
    assert( ((new HTMLTableBuilder).Table() || "" |"Java").toString == "<table><tr><td>Java</td></tr></table>" )

    //The column only
    assert( ((new HTMLTableBuilder).Table() ||"Java" ||"Scala" || "Python").toString ==
            "<table><tr></tr><tr><td>Java</td></tr><tr><td>Scala</td></tr><tr><td>Python</td></tr></table>")

  }

}

