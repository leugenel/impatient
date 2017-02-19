package main

import java.io.IOException

/**
  * Created by eugenel on 2/1/17.
  */
class Chapt5 {
  var value = 10
  println("Here construct")

  def getme :Int = value;

  //Constructor
  def this (nvalue:Int)={
    this()
    value = nvalue
  }
}

//5.2
class BankAccount {

  private var balance:Int = 0

  def deposit (nevValue : Int)  = {balance += nevValue}
  def withdraw (nevValue : Int)  = {
      balance -= nevValue
  }
  def state = balance

}

//5.3
class Time2   {

  private var hours = 0
  private var minutes = 0

  def this (hrsin: Int, minsin: Int) = {
    this()
    if (hrsin > 24 || hrsin < 0 || minsin<0 || minsin>60){
      throw new Exception("The time out of the range")
    }

    hours = hrsin
    minutes = minsin
  }

  def before (other:Time2): Boolean = {
    if (hours < other.hours) true
    else if (hours > other.hours) false
    else if (minutes < other.minutes) true
    else false
  }

}

//5.4
class Time   {
  private var minutes = 0

  def this (hrsin: Int, minsin: Int) = {
    this()
    if (hrsin > 24 || hrsin < 0 || minsin<0 || minsin>60){
      throw new Exception("The time out of the range")
    }
    minutes = (hrsin+1)*60 + minsin
  }

  def before (other:Time): Boolean = {
    if (minutes < other.minutes) true
    else false
  }

}

//5.7
class Person (private val personNameFamily : String){
  if ( personNameFamily.count(_ ==' ') != 1 ) throw new Exception("The name is in the wrong format")
  val Array (firstName, lastName) = personNameFamily.split(" ")
}

//5.8
class Car (private var varManufacter :String,
           private var varModelName: String,
           var varLicensePlate: String = "",
           private var varModelYear: Int = -1 ){

  //Constructors
  def this (manufacter: String, modelName: String,  modelYear: Int){
    this(manufacter, modelName, varModelYear=modelYear)
  }

  def this (manufacter: String, modelName: String,  licensePlate: String ){
    this(manufacter, modelName, varLicensePlate=licensePlate)
  }

  //Interfaces
  def manufacter = varManufacter
  def modelName = varModelName
  def modelYear = varModelYear

}
