package impatient
package test.scala
import java.io.{File, PrintWriter}

import org.scalatest.FunSuite

import scala.io.Source
import scala.util.matching.Regex
/**
  * Created by eugenel on 4/3/17.
  */
class Chapt9Test extends FunSuite{

  test("inverse file"){
    val origFile: String = "example1.txt"
    val reverseFile: String = "example2.txt"
    val out = new PrintWriter(origFile)
    for (l <-  0 to 9){
      out.println(l)
    }
    out.close()
    new Chapt9().reverseFileOrder(origFile, reverseFile)
    val source = Source.fromFile(reverseFile, "UTF-8")
    for (i <- 9 to 0) assert( source.getLines().toArray.apply(i).toString==i.toString)

    new File(origFile).delete()
    new File(reverseFile).delete()

  }

  test("replace tabs to spaces"){
    val origBuffer: String  = "one\ttwo\tthree\tfour\tfive\n1\t22222\t333\t44444\t55555555\n"
    val finalBuffer: String ="one     two     three   four    five    \n1       22222   333     44444   55555555\n"
    val origFile:String = "tabs.txt"
    val out = new PrintWriter(origFile)
    out.print(origBuffer)
    out.close()
    new Chapt9().replaceTabsSpaces(origFile, 8)
    val changed = Source.fromFile(origFile, "UTF-8")
    assert(changed.mkString==finalBuffer)
    new File(origFile).delete()
  }

  test ("print more than 12"){
    val origBuffer: String  = "123456789§11 wrew 123456789§12 sfs 123456789§13 sdaf 123456789§14 tt 12345678901 1234567890123456789"
    val origFile:String = "test.txt"
    val out = new PrintWriter(origFile)
    out.print(origBuffer)
    out.close()
    assert(new Chapt9().moreThan12(origFile)=="123456789§11,123456789§12,123456789§13,123456789§14,1234567890123456789")
    new File(origFile).delete()
  }

  test("Double operations"){
    val origBuffer: String  = "3.76 4.55 9.0 3.89 2.347645 8.32 7.4 4.33"
    val origFile:String = "fnums.txt"
    val out = new PrintWriter(origFile)
    out.print(origBuffer)
    out.close()
    val result : Array[Double] =  new Chapt9().floatsOperation(origFile)
    assert(result.sum==43.597645)
    assert(result.max==9.0)
    assert(result.min==2.347645)
    assert(result.sum/result.length == 5.449705625)
    new File(origFile).delete()
  }

  test("No floats"){
    val origBuffer: String  = "1 2.0 766.5 stsdf 5 789 6.0 dsf sdfsd.eryter .dgd .0rtyt .999 . ere.66 ppp.88"
    val expected :String =  "1,stsdf,5,789,dsf,sdfsd.eryter,.dgd,.0rtyt,.,ere.66,ppp.88"
    val origFile:String = "nofnums.txt"
    val out = new PrintWriter(origFile)
    out.print(origBuffer)
    out.close()
    assert (new Chapt9().justNoFloats(origFile) == expected)
    new File(origFile).delete()
  }


  test("Print Img src"){
    val urlSource:String = "https://toolbox.googleapps.com/apps/har_analyzer"
    val result :Array[String]=Array("", "static/img/imgs/control/folder_open.png", "static/img/imgs/control/collapse_all.png",
                                    "static/img/imgs/control/expand_all.png",
                                    "static/img/imgs/control/search-white.png", "static/img/imgs/harhelp.png")

    val iteratorAllSrc:Iterator[Regex.Match]=new Chapt9().printSrcFromImgTag(urlSource)
    for ((m,i) <- iteratorAllSrc zip (0 to iteratorAllSrc.length).iterator)
          assert(m.group(1)==result(i))
  }

  test ("Calculate extensions"){
    val dir: String = s"/Users/eugenel/Documents/Code/Selen3Try/target"
    val extension : String = ".class"
    assert (new Chapt9().findSpecificExtension(dir, extension) == 5)
  }

}
