package impatient

import scala.io.Source
import java.io.PrintWriter
import java.nio.file.{Files, Paths}

import scala.util.matching.Regex

/**
  * Created by eugenel on 4/3/17.
  */
class Chapt9 {

  //9.1
  def reverseFileOrder(origFile:String, reverseFile:String):Unit = {
    val source = Source.fromFile(origFile, "UTF-8")
    val linesIterator = source.getLines().toArray.reverse
    val out = new PrintWriter(reverseFile)
    for (l <-  linesIterator){
      out.println(l)
    }
    out.close()
  }

  //9.2
  def replaceTabsSpaces(tabFile:String, columnLength: Int):Unit = {
    var outBuffer: String = ""
    for (l <-  Source.fromFile(tabFile).getLines()){
      for (spl <- l.split("""\t""")){
        outBuffer=outBuffer+spl+" "*(columnLength-spl.length)
      }
      outBuffer=outBuffer+"\n"
    }
    val out = new PrintWriter(tabFile)
    out.print(outBuffer)
    out.close()
  }

  //9.3
  //“Write a Scala code snippet that reads a file and prints all words with more than 12 characters to the console.”
  def moreThan12(origFile:String):String ={
    //println(Source.fromFile(origFile, "UTF-8").mkString.split("\\s+").filter(_.length==12).mkString(","))
    Source.fromFile(origFile, "UTF-8").mkString.split("\\s+").filter(_.length>=12).mkString(",")
  }

  //9.4
  //“Write a Scala program that reads a text file containing only floating-point numbers.
  // Print the sum, average, maximum, and minimum of the numbers in the file.”

  def floatsOperation(origFile:String):Array[Double] = {
    Source.fromFile(origFile, "UTF-8" ).mkString.split("\\s+").map(_.toDouble)
  }

  //9.7
  //“Write a Scala program that reads a text file and prints all tokens
  // in the file that are not floating-point numbers”
  def justNoFloats(origFile:String):String = {
    val token = "[0-9]*\\.[0-9]+"
    Source.fromFile(origFile).mkString.split("\\s+").filter(s => !(s matches token) ).mkString(",")
  }

  //9.8
  //“Write a Scala program that prints the src attributes of all img tags of a web page.”
  def printSrcFromImgTag(urlSource:String):Iterator[Regex.Match] = {
    val token = """<img .*src=["|']([^ ]*)["|'] """.r
    token.findAllMatchIn(Source.fromURL(urlSource, "UTF-8").mkString)
  }

  //9.9
  //“Write a Scala program that counts how many files with .class extension
  // are in a given directory and its subdirectories.”
  def findSpecificExtension(dir: String, extension : String):Long = {

    val entries = Files.walk(Paths.get(dir))
    try{
      entries.filter(s => !Files.isDirectory(s) && (s.toString endsWith extension)).count()
    } finally {entries.close()}

  }

}