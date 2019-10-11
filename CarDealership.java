//Name: Marco Bozic
//Student Number: 500896651
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;

//CarDealership class
public class CarDealership
{
  //creating various instance variables
  ArrayList<Car> cars = new ArrayList<Car>();
  SalesTeam salesTeam = new SalesTeam();
  AccountingSystem accountingSystem = new AccountingSystem();
  
  // Filters
  boolean electricFilter = false;
  boolean priceFilter = false;
  double  priceMin    = 0;
  double  priceMax    = 0;
  boolean AWDFilter = false;
  boolean buy;
  double totalSales;
  int buyC = 0;
  int retC = 0;
  int testBuy;
  Car car1;
  Car car2;
  boolean wrongVin = false;
  
  //CarDealership constructor
  public CarDealership(ArrayList<Car> car)
  {
    cars = car;
  }
  
  //method that adds car objects to ArrayList
  public void addCars(ArrayList<Car> newCars)
  {
    if (newCars.size() < 29)
    {
      cars.addAll(newCars);
    }
  }

  
  //method that removes car from ArrayList based on specific VIN
  public String buyCar(int VIN)
  {
    //string variable used to have information of car in string type to be able to output later
    String boughtCar;
    //if statement used to valid VIN is read
    if (VIN < 100 || VIN > 499 || cars.size() == 0)
    {
        System.out.println("VIN OUT OF RANGE!");
    }
    car1 = cars.get(0);
    //for loop used to iterate through cars ArrayList and remove specific car
    for (int i = 0; i < cars.size(); i++)
    {
      car1 = cars.get(i);
      //if statement is used to make sure to access proper car
      if (car1.vin == VIN)
      {
        testBuy = 1;
        car1 = cars.get(i);
        car2 = car1;
        totalSales += cars.get(i).price;
        cars.remove(i);
        String emp = salesTeam.returnSale();
        Calendar calendar = new GregorianCalendar(2019, 4, 20);
        Random randM = new Random();
        buyC += 1;
        calendar.set(Calendar.MONTH, randM.nextInt(11));
        calendar.set(Calendar.DAY_OF_MONTH, randM.nextInt(30));
        boughtCar = accountingSystem.add(calendar,car1,emp,"BUY",car1.price);
        //returns string version of transaction for user to see
        return boughtCar;
      }
      //wrong vin is set to true to be used for output statement out of loop
      else
      {
         wrongVin = true;
      }
    }
    //output statement is executed if wrongVin is true
    if (wrongVin == true)
    {
      System.out.println("INCOMPATABLE VIN!");
    }
    car1 = car2;
    return null;
  }
  
  //return method used to return car to ArrayList and record transaction 
  public String returnCar(int trans)
  {
    String j = null;
    Transaction tr = accountingSystem.getTransaction(trans);
    //if there is a transaction it returns car 
    if (tr != null)
    {
      cars.add(car1);
      int month = tr.date.get(Calendar.MONTH);
      int day = tr.date.get(Calendar.DAY_OF_MONTH);
      Random rand = new Random();
      Calendar c = new GregorianCalendar(2019, month, day + rand.nextInt(30-day));
      retC += 1;
      j =  accountingSystem.add(c, tr.car, tr.salesPerson, "RET", tr.salePrice);
    }
    //outputs transaction
    return j;
  }
  //method used to output information of car along with VIN number
  public void displayInventory()
  {
    if (cars.size() != 0)
    {
      System.out.println("");
      System.out.println("LIST OF AVAILABLE CARS:");
      System.out.println("");
    }
    
	  for (int i = 0; i < cars.size(); i++)
	  {
		Car car = cars.get(i);
		
		if (priceFilter && (car.price < priceMin || car.price > priceMax))
		   continue;
		
		if (electricFilter && car.power != Vehicle.PowerSource.ELECTRIC_MOTOR)
		   continue;
		
		if (AWDFilter && !car.AWD)
		   continue;
		
		System.out.println("VIN: " + car.display());
	  }
  }
  //method that clears all filters
  public void filtersClear()
  {
	  electricFilter = false;
	  priceFilter = false;
	  AWDFilter = false;
  }
  //method that filters cars based on price
  public void filterByPrice(double min, double max)
  {
	  priceFilter = true;
	  priceMin    = min;
	  priceMax    = max;
  }
  //method that filters car based on if it is electric
  public void filterByElectric()
  {
	  electricFilter = true;
  }
  //method that filters car based on if it is AWD
  public void filterByAWD()
  {
	  AWDFilter = true;
  }
  //method that arranges cars based on prices
  public void sortByPrice()
  {
	  Collections.sort(cars);
  }
  //SafetyRatingComparator class that implements Comparator
  private class SafetyRatingComparator implements Comparator<Car>
  {
    //method used to compare two cars based on safety rating
    public int compare(Car a, Car b)
  	{
  	  if      (a.safetyRating < b.safetyRating) return  1;
  	  else if (a.safetyRating > b.safetyRating) return -1;
  	  else                                      return  0;		  
  	}
  }
  //method that arranges cars based on safety ratings 
  public void sortBySafetyRating()
  {
	Collections.sort(cars,new SafetyRatingComparator());
  }
  //RangeComparator class that implements Comparator
  private class RangeComparator implements Comparator<Car>
  {
  	public int compare(Car a, Car b)
  	{
  	  if      (a.maxRange < b.maxRange) return  1;
  	  else if (a.maxRange > b.maxRange) return -1;
  	  else                              return  0;		  
  	}
  }
  //method that arranges cars based on the cars' range
  public void sortByMaxRange()
  {
	Collections.sort(cars,new RangeComparator());
  }
  
  
}
