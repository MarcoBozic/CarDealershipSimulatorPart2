//Name: Marco Bozic
//Student Number: 500896651

//ElectricCar class extends Car class
public class ElectricCar extends Car
{
  int    rechargeTime;//minutes
  String batteryType;
  
  public ElectricCar(String manuf, String color, String model, Vehicle.PowerSource power, 
		     double safety, int range, boolean awd, double price, int rch)
  {
	  super(manuf, color, model, Vehicle.PowerSource.ELECTRIC_MOTOR, safety, range, awd, price);
	  rechargeTime = rch;
	  batteryType = "Lithium";
  }
  //method that sets recharge time based on parameter value
  public void setRechargeTime(int time)
  {
	  rechargeTime = time;
  }
  //method that sets battery type based on parameter value
  public void batteryType(String type)
  {
	  batteryType = type;
  }
  //method that is used to display information about an electric car as a String
  public String display()
  {
	  return super.display() + " " + "EL, BAT: " + batteryType + " RCH: " + rechargeTime;
  }
}
