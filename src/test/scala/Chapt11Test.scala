package impatient

import java.io.{File, PrintWriter}
import java.nio.file.{Path, Paths}

import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

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

  test("BitSequence"){
    val bs = new BitSequence(9)
    assert(bs(0)==1 && bs(1) ==0 && bs(2)==0 && bs(3)==1 )
    assert(bs.toString=="1001")
    //Set all bites to zero
    bs(0) = 0
    bs(3) = 0
    assert(bs.toString=="0")
    assert(bs(0)==0 && bs(1) ==0 && bs(2)==0 && bs(3)==0 )
    //Set all bits 1
    bs(0) = 1
    bs(1) = 1
    bs(2) = 1
    bs(3) = 1
    assert(bs.toString=="1111")
    assert(bs(0)==1 && bs(1) ==1 && bs(2)==1 && bs(3)==1)
  }

  test ("PathComponents"){
    //Prepare some file (will delete it at the end)
    val someFile = new File("example.txt")
    //Build Java object Path for this file
    val mypath:Path = Paths.get(someFile.getAbsolutePath)
    //Our class that includes Path
    val pc:PathComponents = new PathComponents(mypath)
    //This is exctractor
    val PathComponents(path, name) = pc

    assert(path.toString=="/Users/eugenel/Documents/Code/ImpatientScala/impatient")
    assert(name.toString=="example.txt")

    someFile.delete()
  }

  test ("PathComponentsSeq"){
    //Prepare some file (will delete it at the end)
    val someFile = new File("example.txt")
    //Build Java object Path for this file
    val mypath:Path = Paths.get(someFile.getAbsolutePath)
    //Our class that includes Path
    val pc:PathComponentsSeq = new PathComponentsSeq(mypath)
    //This is exctractor
    val PathComponentsSeq(lb @_*) = pc

    assert(lb.mkString(",")=="Users,eugenel,Documents,Code,ImpatientScala,impatient,example.txt")
    someFile.delete()
  }

  test("DynamicProps"){
    var sysProps = new DynamicProps(System.getProperties)

    println(sysProps.user.name)
    sysProps.user.name = "Eugene"
    println(sysProps.user.name)
    println(sysProps.java.home)

    //println(sysProps.java.home) //Error:(123, 27) value home is not a member of String  println(sysProps.java.home)

  }

}

