//Name: Marco Bozic
//Student Number: 500896651
import java.util.Collections;
import java.util.Comparator;

//Car class extends Vehicle class and implements Comparable
public class Car extends Vehicle implements Comparable<Car>
{
  //values for car model
 // public static enum Model
  //{
	 // SEDAN, SUV, SPORTS, MINIVAN;
  //}
  //initialize various variables
  //Model   model; 
  String model;
  int     maxRange; 
  double  safetyRating;
  boolean AWD;
  double  price;
  final String SEDAN = "SEDAN";
  final String SUV = "SUV";
  final String SPORTS = "SPORTS";
  final String MINIVAN = "MINIVAN";
  
  //public Car()
  //{
	//  this.model = Model.SEDAN;
  //}
  //constructor for Car class
  public Car(String manuf, String color, String model, Vehicle.PowerSource power, double safety, int range, boolean awd, double price)
  {
	  super(manuf, color, 4, power);
	  this.model = model;
	  this.price = price;
	  AWD = awd;
	  safetyRating = safety;
	  maxRange = range;
  }
  //used to display string information for car object
  public String display()
  {
	  return super.display() + " " + model + " " + price + "$" + " SF: " + safetyRating + " RNG: " + maxRange;
  }
  //toString method
  public String toString()
  {
	return "";
  }
  
  //equals method
  public boolean equals(Object other)
  {
	  Car otherCar = (Car) other;
	  return super.equals(other) && this.model == otherCar.model && this.AWD == otherCar.AWD; 
  }
  //compare method to compare car objects
  public int compareTo(Car other)
  {
    if      (this.price > other.price) return  1;
	else if (this.price < other.price) return -1;
	else                               return  0;
  }
}
