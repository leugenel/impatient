package MMExp

/**
  * Created by eugenel on 5/13/17.
  * Some calculation to Michelson-Morley experiment
  * Plane to directions A-->B with wind velocity
  * The math formula is Total time = 2*Distance/(Velocity_Plane*(1-(VelocityWind^2/VelocityPlan^2)))
  */
class Plane2Directions(PlanVelocity:Double, WindVelocity:Double, Distance:Double) {

  def totalTime: Double = {
    (2*Distance)/(PlanVelocity*(1 - (WindVelocity*WindVelocity)/(PlanVelocity*PlanVelocity) ))
  }

  override def toString: String = {
    "Distance : "+ Distance+" Plane Velocity: " + PlanVelocity+" Wind Velocity: "+ WindVelocity
  }

}
