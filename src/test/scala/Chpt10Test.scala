package impatient
import org.scalatest.FunSuite

/**
  * Created by eugenel on 4/18/17.
  */
class Chpt10Test extends FunSuite{

  test("RectangleLike trait") {
    val egg = new java.awt.geom.Ellipse2D.Double(5, 10, 20, 30) with RectangleLike
    assert(egg.getX==5.0 && egg.getY==10.0 && egg.getWidth==20.0 && egg.getHeight==30.0)
    egg.translate(10, -10)
    assert(egg.getX==15.0 && egg.getY==0.0 && egg.getWidth==20.0 && egg.getHeight==30.0)
    egg.grow(10, 20)
    assert(egg.getX==5.0 && egg.getY == -20.0 && egg.getWidth==40.0 && egg.getHeight==70.0)
  }
}
