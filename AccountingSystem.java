//Name: Marco Bozic
//Student Number: 500896651
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.ArrayList;

//AccountingSystem class
public class AccountingSystem
{
    //creating various instance variables
    static ArrayList<Transaction> transactions;
    static ArrayList<Integer> HighM = new ArrayList<Integer>();
    static int counter = -1;
    static ArrayList<String> months = new ArrayList<String>();
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, YYYY");

    //constructor for AccountingSystem
    public AccountingSystem()
    {
        //creating ArrayList to store transactions
        transactions = new ArrayList<Transaction>();
    }

    //add method used to add transaction objects to transactions ArrayList when called
    public String add(Calendar date, Car car, String salesPerson, String type, double salePrice)
    {
        Random rand = new Random();
        int id1 = (int)(Math.random() * 99 + 1);
        Transaction transaction = new Transaction(id1, date, car, salesPerson, type, salePrice);
        transactions.add(transaction);
        counter += 1;
        
        for (int i = 1; i <= 12; i++)
        {
            months.add(" ");
            HighM.add(0);
        }
        //for loop used to iterate through months and check if it equals month specific to transaction
        for (int i = 1; i <= 12; i++)
        {
            if (date.get(date.MONTH) == i)
            {
                months.set(i, "\nTransaction ID: " + id1 + " " + sdf.format(date.getTime()) + " " + type + " Salesperson: " + salesPerson + " Car: " + car.display() + months.get(i));
                HighM.set(i,HighM.get(i) + 1);
            }
        }
        //display transaction as a String
        return transaction.display();
    }

    //used to get transaction based on id and then loops through transactions ArrayList to find specific transction to return
    public Transaction getTransaction(int id2)
    {
        Transaction tran = null;
        for (int i = 0; i < transactions.size(); i++)
        {
            if (id2 == transactions.get(i).id)
            {
                tran = transactions.get(i);
            }
        }
        return tran;
    }
}