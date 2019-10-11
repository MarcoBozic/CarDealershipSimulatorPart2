//Name: Marco Bozic
//Student Number: 500896651
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

//Transaction class
public class Transaction
{
    //various instance variables
    int id;
    Calendar date;
    Car car;
    String salesPerson;
    String type;
    double salePrice;
    SimpleDateFormat sdf = new SimpleDateFormat("MM dd, YYYY");
    //Transaction constructor
    public Transaction(int id, Calendar date, Car car, String salesPerson, String type, double salePrice)
    {
        this.id = id;
        this.date = date;
        this.car = car;
        this.salesPerson = salesPerson;
        this.type = type;
        this.salePrice = salePrice;
    }
    //method used to display transaction information as a String
    public String display()
    {
        //SimpleDateFormat sdf = new SimpleDateFormat("EE MM/dd, YYYY");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd, yyyy");
        return "Transaction ID: " + id + " Date: " + sdf.format(date.getTime()) + " Transaction Type: " + type + " Salesperson: " + salesPerson + " Car: " + car.display();
        
    }
}