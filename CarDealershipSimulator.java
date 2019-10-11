//Name: Marco Bozic
//Student Number: 500896651
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

//CarDealershipSimulator class
public class CarDealershipSimulator 
{
	ArrayList<Car> carObjects = new ArrayList<Car>();
	//main method which is used for the simulator
	public static void main(String[] args) throws IOException
  {
	  //various instance variables
	  CarDealership dealership;
	  Car currentCar = null;
	  int carIndex = -1;
	  SalesTeam salesTeam = new SalesTeam();
	  AccountingSystem accountingSystem = new AccountingSystem();
	  Transaction tran;
		ArrayList<Car> newCars = new ArrayList<Car>();
		dealership = new CarDealership(newCars);
		boolean canAdd = true;
		boolean canList = false;
		boolean quitWarning = true;

		//user must update file path in order for program to work
		Scanner inputFile = new Scanner(new File("/Users/marcobozic/Documents/Assignment2/cars.txt"));
		int x = 1;
		
		//program will run as long as no exceptions are found
		do{
		//try and except used to not let program crash
		try{
		//method that goes through file and makes each line into a car object
    while(inputFile.hasNextLine())
    {
      String line = inputFile.nextLine();
      String[] splitLine = line.split(" ");
			boolean awd;

			//checks to see if car is AWD or not
      if(splitLine[6].equals("2WD"))
      {
          awd = false;
      }
      else
      {
        awd = true;
      }
			//method that checks if car has a gas engine or electric motor
      if(splitLine[3].equals("GAS_ENGINE"))
      {
        Car gasCar = new Car(splitLine[0], splitLine[1], splitLine[2], Vehicle.PowerSource.GAS_ENGINE, Double.parseDouble(splitLine[4]), Integer.parseInt(splitLine[5]), awd, Double.parseDouble(splitLine[7]));
        newCars.add(gasCar);
      }

      else if(splitLine[3].equals("ELECTRIC_MOTOR"))
      {
        Car electricCar = new ElectricCar(splitLine[0], splitLine[1], splitLine[2], Vehicle.PowerSource.ELECTRIC_MOTOR, Double.parseDouble(splitLine[4]), Integer.parseInt(splitLine[5]), awd, Double.parseDouble(splitLine[7]), Integer.parseInt(splitLine[8]));
        newCars.add(electricCar);
      }

		} 
		inputFile.close();
		
		//used if cars are not taken from file
	  //newCars.add(new Car("Toyota", "blue", Car.Model.SEDAN, 
		//	      Vehicle.PowerSource.GAS_ENGINE, 9.5, 500, false, 25000));
	  //newCars.add(new Car("Honda", "red", Car.Model.SPORTS, 
			//  Vehicle.PowerSource.GAS_ENGINE, 9.2, 450, false, 30000));
	  //newCars.add(new Car("Kia", "white", Car.Model.MINIVAN, 
			//  Vehicle.PowerSource.GAS_ENGINE, 9.7, 550, false, 20000));
	  //newCars.add(new Car("BMW", "black", Car.Model.SEDAN, 
			//  Vehicle.PowerSource.GAS_ENGINE, 9.6, 600, true, 55000));
	  //newCars.add(new ElectricCar("Tesla", "red", Car.Model.SEDAN, 
			//  Vehicle.PowerSource.ELECTRIC_MOTOR, 9.1, 425, true, 85000, 30));
	  //newCars.add(new Car("Chevy", "red", Car.Model.MINIVAN, 
			//  Vehicle.PowerSource.GAS_ENGINE, 9.25, 475, false, 40000));
	  //newCars.add(new ElectricCar("ChevyVolt", "green", Car.Model.SEDAN, 
			//  Vehicle.PowerSource.ELECTRIC_MOTOR, 8.9, 375, true, 37000, 45));
	  //newCars.add(new Car("Bentley", "black", Car.Model.SEDAN, 
		//	  Vehicle.PowerSource.GAS_ENGINE, 9.8, 575, false, 150000));
	  //newCars.add(new ElectricCar("NissanLeaf", "green", Car.Model.SEDAN, 
			 // Vehicle.PowerSource.ELECTRIC_MOTOR, 8.8, 325, true, 32000, 55));
	  
		//file that has all cars and will be read by a scanner
	  int id = -1;
		int numValue = 0;
		boolean canReturn = false;
		Scanner scanner = new Scanner(System.in);
	  System.out.print(">");
	  //used to check user input
	  while (scanner.hasNextLine())
	  {
			String inputLine = scanner.nextLine();
		  if (inputLine == null || inputLine.equals("")) 
          {
						System.out.println("ENTER A COMMAND!");
						System.out.print("\n>");
            continue;
		  }
		  
			Scanner commandLine = new Scanner(inputLine);
		  String command = commandLine.next();
			//various if statements perform actions based on user input
		  if (command == null || command.equals("")) 
		  {
             System.out.print("\n>");
	         continue;
          }
		  else if (command.equals("L"))
		  {
				if (canList == true)
				{
					dealership.displayInventory();
				}
				else
				{
					System.out.println("CANNOT SHOW LIST UNTIL CARS ARE ADDED!");
				}
		  }
		  else if (command.equals("Q") || command.equals("QUIT"))
		  {	  
				if (quitWarning == true)
				{
					System.out.println("WARNING YOU ARE ABOUT TO END THE PROGRAM!");
					System.out.println("ENTER " + "\"Q\"" + " TO CONFIRM");
					quitWarning = false;
				}
				else if (quitWarning == false)
				{
					System.exit(0);
				}
		  }
		  else if (command.equals("BUY"))
		  {
				
				if (commandLine.hasNextInt())
				{
				int vinInput = commandLine.nextInt();  
			  String carBought = dealership.buyCar(vinInput);
					if (carBought != null)
					{
						System.out.println(carBought);
						String[] split = carBought.split(" ");
						canReturn = true;
						Scanner sc = new Scanner(carBought);
						id = Integer.parseInt(split[2]);
					}
				}
				else
				{
					System.out.println("PLEASE ENTER A PROPER VIN ID!");
				}
		  }
		  else if (command.equals("RET"))
		  { 
			if (id != -1 && canReturn == true)
			  {
					String returnPurchase = dealership.returnCar(id);
					System.out.println(returnPurchase);
					currentCar = null;
					canReturn = false;
				}
				else
				{	
					System.out.println("THERE IS NOTHING TO RETURN!");
				}
		  }
		  else if (command.equals("ADD"))
		  {
				if (canAdd == true)
				{
					dealership.addCars(newCars);
					System.out.println("SUCCESSFULLY ADDED CARS");
					System.out.println("USE " + "\"L\"" + " TO VIEW CHANGES");
					canList = true;
					canAdd = false;
				}
				else
				{
					System.out.println("CANNOT ADD MORE THAN ONCE!");
				}
		  }
		  else if (command.equals("SPR"))
		  {
				dealership.sortByPrice();
				System.out.println("SUCCESSFULLY SORTED BY PRICE");
				System.out.println("USE " + "\"L\"" + " TO VIEW CHANGES");
		  }
		  else if (command.equals("SSR"))
		  {
				dealership.sortBySafetyRating();
				System.out.println("SUCCESSFULLY SORTED BY SAFETY RATING");
				System.out.println("USE " + "\"L\"" + " TO VIEW CHANGES");
		  }
		  else if (command.equals("SMR"))
		  {
				dealership.sortByMaxRange();
				System.out.println("SUCCESSFULLY SORTED BY MAX RANGE");
				System.out.println("USE " + "\"L\"" + " TO VIEW CHANGES");
		  }
		  else if (command.equals("FPR"))
		  {
			  double minPrice = 0; 
			  double maxPrice = 0;
			  // Filter inputs
			  if (!commandLine.hasNextDouble())
			  {				  
				  System.out.println("INVALID ARGUMENTS!");
				  continue;
			  }
			  minPrice = commandLine.nextDouble();
			  if (!commandLine.hasNextDouble())
			  {				  
				  System.out.println("INVALID ARGUMENTS!");
				  continue;
			  }	  
		      maxPrice = commandLine.nextDouble();
		      if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice)
		      {				  
				  System.out.println("INVALID ARGUMENTS!");
				  continue;
			  }	
				dealership.filterByPrice(minPrice,maxPrice);
				System.out.println("SUCCESSFULLY FILTERED BY PRICE");
				System.out.println("USE " + "\"L\"" + " TO VIEW CHANGES OR USE " + "\"FCL\"" + " TO CLEAR ALL FILTERS");
		  }
		  else if (command.equals("FEL"))
		  {
				dealership.filterByElectric();
				System.out.println("SUCCESSFULLY FILTERED BY ELECTRIC CARS");
				System.out.println("USE " + "\"L\"" + " TO VIEW CHANGES");
				System.out.println("USE " + "\"L\"" + " TO VIEW CHANGES OR USE " + "\"FCL\"" + " TO CLEAR ALL FILTERS");
		  }
		  else if (command.equals("FAW"))
		  {
				dealership.filterByAWD();
				System.out.println("SUCCESSFULLY FILTERED BY AWD");
				System.out.println("USE " + "\"L\"" + " TO VIEW CHANGES");
				System.out.println("USE " + "\"L\"" + " TO VIEW CHANGES OR USE " + "\"FCL\"" + " TO CLEAR ALL FILTERS");
		  }
		  else if (command.equals("FCL"))
		  {
				dealership.filtersClear();
				System.out.println("SUCCESSFULLY CLEARED FILTERS");
				System.out.println("USE " + "\"L\"" + " TO VIEW CHANGES");
			} 
			else if (command.equals("SALES")) {

				int monthNumber = -1;

				if (commandLine.hasNext() && !(commandLine.hasNextInt())) 
				{
					String nextLine = commandLine.next();

					if (nextLine.equals("TEAM")) 
					{
						System.out.println(salesTeam.display());
					}

					else if (nextLine.equals("TOPSP")) {
						int max = 0;
						String name = "FIRST";

						if (dealership.testBuy > 0) {

							for (int j = 0; j < salesTeam.sales.size(); j++) {

								if (salesTeam.sales.get(j) > max) 
								{
									max = salesTeam.sales.get(j);
									name = salesTeam.people.get(j);
								}
							}
							for (int j = 0; j < salesTeam.sales.size(); j++) {
								if (salesTeam.sales.get(j) == max && !salesTeam.people.get(j).equals(name)) 
								{
									name = salesTeam.people.get(j) + ", " + name;
								}

							}
							if (max != 0)
								System.out.println("Top SP: " + name + " " + max);

						}
					} 
					else if (nextLine.equals("STATS")) 
					{
						int maxM =0;
						String HighMonth="";
						Calendar calendar = new GregorianCalendar(2013,1,28,13,24,56);
						Calendar calendar2 = new GregorianCalendar(2013,1,28,13,24,56);
						SimpleDateFormat sdf = new SimpleDateFormat("MMM");
						int monthH =0;
						String str ="";
						for (int i=0; i< accountingSystem.HighM.size(); i++)
						{
						if (accountingSystem.HighM.get(i)>maxM)
						{
							maxM =accountingSystem.HighM.get(i) ;
							monthH=i;
							calendar.set(Calendar.MONTH, i);
							str = sdf.format(calendar.getTime());
						}
						else if (accountingSystem.HighM.get(i)==maxM)
						{
							calendar.set(Calendar.MONTH, i);
							str = str + " " +sdf.format(calendar.getTime());
						}
						}
						System.out.println("Total Sales: " + dealership.totalSales + " Total Sold: " + dealership.buyC + " Average Sales: " + (int) dealership.totalSales/12 + " Total Returned: " + dealership.retC + " Best Month: " +str+ " Cars Sold in " + str + ": " + maxM);
					}
					else
					{
						System.out.println("INVALID COMMAND!");
					}

				} 
				else if (!(commandLine.hasNextInt())) 
				{
					for (int i = 0; i < accountingSystem.transactions.size(); i++) 
					{
						System.out.println(accountingSystem.transactions.get(i).display());
					}
				}
				if (commandLine.hasNextInt()) 
				{
					monthNumber = commandLine.nextInt();
					for (int i = 0; i < accountingSystem.months.size(); i++) {

						if (monthNumber == i)
						{
							System.out.println(accountingSystem.months.get(i));
							System.out.println("");
						}
					}
				}

			}
			else
			{
				System.out.println("INVALID COMMAND!");
			}
			System.out.print("\n>");
	}
			}
			catch(Exception e)
			{
				System.out.println("PLEASE TRY AGAIN!");
			}
		}
		//program will continue to run while x is one
		while (x == 1);
		

}
}