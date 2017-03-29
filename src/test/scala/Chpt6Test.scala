package impatient
package test.scala {

  import main._
  import org.scalatest.FunSuite

  /**
    * Created by eugenel on 2/7/17.
    */
  class Chpt6Test extends FunSuite {

    test("Conversions: miles to kilometers") {
      assert(milesToKilometers.Conversion(10) == 16.0934)
    }

    test("Conversions: gallon to liter") {
      assert(gallonsToLiters.Conversion(10) == 37.8541)
    }

    test("Conversions:  inches to cm") {
      assert(inchesToCentimeters.Conversion(10) == 25.4)
    }

    test("Conversions:  negative input") {
      assertThrows[java.lang.Exception] {
        inchesToCentimeters.Conversion(-1)
      }
    }

    test("Conversions: zero input") {
      assertThrows[java.lang.Exception] {
        inchesToCentimeters.Conversion(0)
      }
    }

    test("Point: two objects") {
      val p1 = Point(1, 2)
      val p2 = Point(3, 4)
      assert(p1.x == 1 && p1.y == 2)
      assert(p2.x == 3 && p2.y == 4)
    }

    test("CardSuits: string check") {
      //println("Clubs:"+CardSuits.clubs)
      //println("diamonds:"+CardSuits.diamonds)
      //println("hearts:"+CardSuits.hearts)
      //println("spades:"+CardSuits.spades)
      assert(CardSuits.clubs.toString.equals("♣"))
      assert(CardSuits.diamonds.toString.equals("♦"))
      assert(CardSuits.hearts.toString.equals("♥"))
      assert(CardSuits.spades.toString.equals("♠"))
    }

    test("CardSuits: color check") {
      assert(CardSuits.clubs.isBlack)
      assert(CardSuits.diamonds.isRed)
      assert(CardSuits.hearts.isRed)
      assert(CardSuits.spades.isBlack)

      assert(CardSuits.getColor(CardSuits.clubs).equals("B"))
      assert(CardSuits.getColor(CardSuits.diamonds).equals("R"))
      assert(CardSuits.getColor(CardSuits.hearts).equals("R"))
      assert(CardSuits.getColor(CardSuits.spades).equals("B"))
    }

  }

}