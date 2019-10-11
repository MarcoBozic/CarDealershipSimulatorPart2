//Name: Marco Bozic
//Student Number: 500896651
import java.util.*;

//SalesTeam class
public class SalesTeam
{
    //various instance variables
    LinkedList<String> people;
    Random rand = new Random();
    static ArrayList<Integer> sales = new ArrayList<>();
    //SalesTeam constructor
    public SalesTeam()
    {
        people = new LinkedList<String>();
        people.add("Marco");
        people.add("Blueface");
        people.add("Kodak");
        people.add("Kanye");
        people.add("Biggie");
        
        for (int i = 0; i < people.size(); i++)
        {
            sales.add(0);
        }
    }
    //method that is used when return is made
    public String returnSale()
    {
        int num = rand.nextInt(5);
        int incSale = sales.get(num) + 1;
        sales.set(num, incSale);
        return people.get(num);
    } 
    //method that returns sales team as a String
    public String display()
    {
        ListIterator<String> iterator = people.listIterator();
        String display = "";
        
        while (iterator.hasNext())
        {
            display += " " + iterator.next();
        }
        return "Team:" + display;
    }

    
}