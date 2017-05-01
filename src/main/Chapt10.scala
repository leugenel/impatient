package impatient
import java.beans.{PropertyChangeEvent, PropertyChangeListener, PropertyChangeSupport}
/**
  * Created by eugenel on 4/18/17.
  */
class Chapt10 {
}

//Ex1
//The java.awt.Rectangle class has useful methods translate and grow that are unfortunately
// absent from classes such as java.awt.geom.Ellipse2D. In Scala, you can fix this problem.
// Define a trait RectangleLike with concrete methods translate and grow.
//Please see my comment here
// http://stackoverflow.com/questions/9169500/enhance-java-classes-using-traits-how-to-declare-inside-trait-the-java-fields
trait RectangleLike extends java.awt.geom.RectangularShape {

  val rect = new java.awt.Rectangle

  def translate(dx: Int, dy: Int): Unit = {
    rectHelper(rect.translate(dx, dy))
  }

  def grow(h: Int, v: Int): Unit = {
    rectHelper(rect.grow(h, v))
  }

  def rectHelper(geomFunc:  => Unit) : Unit = {
    rect.setRect(getX, getY, getWidth, getHeight)
    geomFunc
    setFrame(rect.getX, rect.getY, rect.getWidth, rect.getHeight)
  }
}

//2
//Define a class OrderedPoint by mixing scala.math.Ordered[Point] into java.awt.Point. Use lexicographic ordering,
// i.e. (x, y) < (x’, y’) if x < x’ or x = x’ and y < y’
class OrderedPoint(x:Int, y:Int) extends java.awt.Point(x, y) with scala.math.Ordered[Point]{
  override def compare(that: Point):Int = {
    if( (this.getX < that.x) || (this.getX == that.x) && (this.getY < that.y) ) -1
    else if(this.getX==that.x && this.getY==that.y) 0
    else 1

  }
  def compare(that: OrderedPoint):Int = {
      this.compare(new Point(that.x, that.y))
  }
}

//4
//Provide a CryptoLogger trait that encrypts the log messages with the Caesar cipher.
// The key should be 3 by default, but it should be overridable by the user.
// Provide usage examples with the default key and a key of –3.

//From https://github.com/BobNisco/Caesar-Cipher/blob/master/Caesar.scala
// shftAmt the amount of characters that str should be shifted
// Caesar will have an attribute of str
class Caesar(var str:String, shftAmt: Int = 3) {
  //return an encryptd string based on the given shftAmt
  def encrypt(encrypt: Boolean = true): String = {
    val newShftAmt:Int = (shftAmt % 26) * (if(encrypt) 1 else -1)
    val charArray = this.str.toUpperCase().toCharArray
    str="" //we zero it because we already have the previous in the charArray
    for (i <- charArray) {
      if (i.toInt == 32) {
        str += " "
      } else {
        var temp = (i.toInt - 65 + newShftAmt) % 26
        if (temp < 0) {
          temp += 26
        }
        str += (temp + 65).toChar
      }
    }
    str
  }

  def decrypt(): String = {
     encrypt(false)
  }
}

trait Logger {
  def log(msg: String):String =  {""}
}

trait CryptoLogger extends Logger {
  var shift = 3
  override def log(msg: String):String = {
    new Caesar(msg, shift).encrypt()
  }
}

 class Sample extends Logger {
    def doSomething():String = {
      log("doing something")//this not the convential log but some simulation that we can use in the tests
    }
}

//5
//The JavaBeans specification has the notion of a property
// change listener, a standardized way for beans to communicate changes in their properties.
// The PropertyChangeSupport class is provided as a convenience superclass
// for any bean that wishes to support property change listeners.
// Unfortunately, a class that already has another superclass—such as
// JComponent—must reimplement the methods. Reimplement PropertyChangeSupport as a trait,
// and mix it into the java.awt.Point class


trait PropertyChangeSupportTrait {

  //Implementing just minimum of PropertyChangeSupport interfaces
  val pcs = new PropertyChangeSupport()

  def firePropertyChange(propertyName:String, oldValue:Object,  newValue:Object) {
    pcs.firePropertyChange(propertyName, oldValue, newValue)
  }

  def addPropertyChangeListener(listener:PropertyChangeListener) {
    println("addPropertyChangeListener")
    pcs.addPropertyChangeListener(listener)
  }

   def removePropertyChangeListener(listener:PropertyChangeListener) {
     println("removePropertyChangeListener")
     pcs.removePropertyChangeListener(listener)
  }
}

class myPoint extends java.awt.Point with PropertyChangeSupportTrait {

  val move:String = "move"
  val setLocation:String = "setLocation"

  override def move(x: Int, y: Int): Unit = {
    val p:Point = new Point(getX.toInt, getY.toInt)
    super.move(x, y)
    firePropertyChange(move, p, getLocation )
  }

  override def setLocation(x: Int, y: Int): Unit = {
    val p:Point = new Point(getX.toInt, getY.toInt)
    super.setLocation(x, y)
    firePropertyChange(setLocation, p, getLocation )
  }

}

class PointPropertyChangeListener extends PropertyChangeListener {
  var savedEvent : PropertyChangeEvent = _
  def propertyChange(evt: PropertyChangeEvent ) {
    savedEvent=evt
  }
}
