//Name: Marco Bozic
//Student Number: 500896651
import java.util.*;

//Vehicle Class
public class Vehicle 
{
	//values for PowerSource
	public enum PowerSource
	{
		GAS_ENGINE, DIESEL_ENGINE, ELECTRIC_MOTOR;
	}
	//various instance variables
	public PowerSource power;
	String manuf;
	String color;
	int    numWheels;
	int    vin;
	Random rand = new Random();
	
	public Vehicle()
	{
		this.manuf = "";
	}
	//vehicle constructor
	public Vehicle(String manuf, String color, int numWheels, PowerSource power)
	{
	  this.manuf     = manuf;
	  this.color     = color;
	  this.numWheels = numWheels;
	  this.power     = power;
	  vin 			 = rand.nextInt(499-100) + 100; 
	}
	//method used to display information about vehicle
	public String display()
	{
		return vin + " " + manuf + " " + color;
	}
	//method used to vehicle equal object and other vehicle
	public boolean equals(Object other)
	{
		Vehicle otherV = (Vehicle) other;
		return power == otherV.power && manuf.equals(otherV.manuf) && numWheels == otherV.numWheels;
	}
}
