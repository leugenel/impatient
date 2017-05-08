package impatient
import java.io.{File, FileInputStream, PrintWriter}

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

  test ("OrderedPoint trait"){
    val op1 = new OrderedPoint(1,2)
    val op2 = new OrderedPoint(3,4)
    assert(op1.compare(op2) == -1)
    assert(op2.compare(op1) == 1)
    assert(op1.compare(op1) == 0)
    assert(op1.equals(op1)) //Method from the point class
    op1.translate(5,10)
    assert(op1.compare(op2) == 1)
    assert(op2.compare(op1) == -1)
    assert(op1.compare(op1) == 0)

  }

  test ("Caesar cipher"){
    val msg:String = "doing something"
    val cph:String = new Caesar(msg).encrypt()
    val cph2:String = new Caesar(msg, -3).encrypt()

    val someth = new Sample with CryptoLogger
    assert(cph==someth.doSomething())
    someth.shift = -3
    assert(cph2==someth.doSomething())
  }

  test ("PropertyChangeSupport"){
    val mp = new myPoint
    val listener = new PointPropertyChangeListener
    mp.addPropertyChangeListener(listener)
    mp.move(10, 5)
    assert(listener.savedEvent.getPropertyName=="move")
    assert(listener.savedEvent.getOldValue.toString=="java.awt.Point[x=0,y=0]")
    assert(listener.savedEvent.getNewValue.toString=="java.awt.Point[x=10,y=5]")
    mp.setLocation(15,30)
    assert(listener.savedEvent.getPropertyName=="setLocation")
    assert(listener.savedEvent.getOldValue.toString=="java.awt.Point[x=10,y=5]")
    assert(listener.savedEvent.getNewValue.toString=="java.awt.Point[x=15,y=30]")
    mp.removePropertyChangeListener(listener)
  }

  test ("BufferInputStream"){
    val origBuffer: String ="Hello\nBuffer\nTest\n"
    val origFile:String = "test.txt"
    val out = new PrintWriter(origFile)
    out.print(origBuffer)
    out.close()
    val mystream = new ReadFileWithBuffer with DataInputStreamTrait
    val file = new File("test.txt")
    mystream.setStream( new FileInputStream(origFile) )
    mystream.b = new Array[Byte](file.length.toInt)
    mystream.read()
    assert( origBuffer==mystream.log(mystream.b.map(_.toChar).mkString) )
    new File(origFile).delete()
  }

}
