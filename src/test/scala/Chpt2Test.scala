package impatient
package test.scala {

  import main._
  import org.scalatest.FunSuite


  /**
    * Created by eugenel on 1/8/17.
    *
    * Good explantion http://www.scalatest.org/user_guide/using_assertions
    */
  class Chpt2Test extends FunSuite {

    test(" signum: this is should be 1") {
      assert(Chapt2.signum(10) == 1)
    }

    test(" signum: this is should be 0") {
      assert(Chapt2.signum(0) == 0)
    }

    test(" signum: this is should be -1") {
      assert(Chapt2.signum(-10) == -1)
    }
  }

  class Chpt5Test extends FunSuite {

    //class Time
    test(" class Time: time1 before time2 in hours") {
      val time1 = new Time(9, 23)
      val time2 = new Time(10, 20)
      assert(time1.before(time2))
    }

    test(" class Time: time2 before time1 in hours") {
      val time1 = new Time(9, 23)
      val time2 = new Time(7, 20)
      assert(!time1.before(time2))
    }

    test(" class Time: time1 before time2 in minutes") {
      val time1 = new Time(10, 23)
      val time2 = new Time(10, 27)
      assert(time1.before(time2))
    }

    test(" class Time: time2 before time1 in minutes") {
      val time1 = new Time(7, 23)
      val time2 = new Time(7, 20)
      assert(!time1.before(time2))
    }

    test(" class Time: time2 == time1 need be false") {
      val time1 = new Time(7, 23)
      val time2 = new Time(7, 23)
      assert(!time1.before(time2))
    }

    test(" class Time: check around 0") {
      val time1 = new Time(0, 0)
      val time2 = new Time(0, 1)
      assert(time1.before(time2))
    }

    test(" class Time: more check around 0") {
      val time1 = new Time(0, 7)
      val time2 = new Time(0, 10)
      assert(time1.before(time2))
    }

    test(" class Time:  check around 24") {
      val time1 = new Time(24, 0)
      val time2 = new Time(23, 59)
      assert(!time1.before(time2))
    }

    test(" class Time: Verify that entry hours in the range ") {
      assertThrows[java.lang.Exception] {
        new Time(-7, 23)
      }
    }

    test(" class Time: Verify that entry minutes in the range ") {
      assertThrows[java.lang.Exception] {
        new Time(7, -23)
      }
    }

    //class Person
    test(" class Person: simple name") {
      val p = new Person("Eugene Leibovich")
      assert(p.firstName.equals("Eugene"))
      assert(p.lastName.equals("Leibovich"))
    }

    test(" class Person: complicated wrong name") {
      assertThrows[java.lang.Exception] {
        new Person("Eugene Leibovich Evgeny")
      }
    }

    test(" class Person: error name") {
      assertThrows[java.lang.Exception] {
        new Person("EugeneLeibovich")
      }
    }

    test(" class Person: Nothing instead name") {
      assertThrows[java.lang.Exception] {
        new Person("")
      }
    }

    //class Car
    test(" class Car: all variables") {
      val c = new Car("Suzuki", "Vitara", "94-28-18-4", 2017)
      assert(c.modelName.equals("Vitara"))
      assert(c.manufacter.equals("Suzuki"))
      assert(c.varLicensePlate.equals("94-28-18-4"))
      assert(c.modelYear == 2017)
    }

    test(" class Car: only 2 variables") {
      val c = new Car("Suzuki", "Vitara")
      assert(c.modelName.equals("Vitara"))
      assert(c.manufacter.equals("Suzuki"))
      assert(c.varLicensePlate.equals(""))
      assert(c.modelYear == -1)
    }

    test(" class Car: 3 variables with license plate") {
      val c = new Car("Suzuki", "Vitara", "94-28-18-4")
      assert(c.modelName.equals("Vitara"))
      assert(c.manufacter.equals("Suzuki"))
      assert(c.varLicensePlate.equals("94-28-18-4"))
      assert(c.modelYear == -1)
    }

    test(" class Car: 3 variables with Year") {
      val c = new Car("Suzuki", "Vitara", 2017)
      assert(c.modelName.equals("Vitara"))
      assert(c.manufacter.equals("Suzuki"))
      assert(c.varLicensePlate.equals(""))
      assert(c.modelYear == 2017)
    }

    test(" class Car: change the license plate") {
      val c = new Car("Suzuki", "Vitara", "94-28-18-4", 2017)
      assert(c.modelName.equals("Vitara"))
      assert(c.manufacter.equals("Suzuki"))
      assert(c.varLicensePlate.equals("94-28-18-4"))
      assert(c.modelYear == 2017)
      c.varLicensePlate = "65-740-64"
      assert(c.varLicensePlate.equals("65-740-64"))
    }

  }

}