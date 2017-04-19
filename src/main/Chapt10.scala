package impatient
import java.awt.geom.Ellipse2D.Double
/**
  * Created by eugenel on 4/18/17.
  */
class Chapt10 {


}

//The java.awt.Rectangle class has useful methods translate and grow that are unfortunately
// absent from classes such as java.awt.geom.Ellipse2D. In Scala, you can fix this problem.
// Define a trait RectangleLike with concrete methods translate and grow.”

trait RectangleLike extends java.awt.geom.RectangularShape {
  def translate(dx: Int, dy: Int): Unit = {
    val rect = new java.awt.Rectangle
    rectHelper(rect, rect.translate(dx, dy))
  }
  def grow(h: Int, v: Int): Unit = {
    val rect = new java.awt.Rectangle
    rectHelper(rect, rect.grow(h, v))
  }
  def rectHelper(rect: java.awt.Rectangle, geoFunc:  => Unit) : Unit = {
    rect.setRect(getX, getY, getWidth, getHeight)
    geoFunc
    setFrame(rect.getX, rect.getY, rect.getWidth, rect.getHeight)
  }

}

