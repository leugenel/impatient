package test.scala

import main.Chapt2
import org.scalatest.FunSuite


/**
  * Created by eugenel on 1/8/17.
  */
class Chpt2Test extends FunSuite {

    test(" signum: this is should be 1"){
      assert(Chapt2.signum(10) == 1)
    }

    test(" signum: this is should be 0"){
      assert(Chapt2.signum(0) == 0)
    }

    test(" signum: this is should be -1"){
      assert(Chapt2.signum(-10) == -1)
    }

}
