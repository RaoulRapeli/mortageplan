/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author raoul
 */
public class mortageplanTest {
    
 
    public void testAmountOfCustoemrs() throws FileNotFoundException{

        //
        int amountcustomers=0;
        int expResult = 5;
        
        //opens and imports all data from file.
       File myObj = new File("src/main/resources/prospects.txt");
       Scanner myReader = new Scanner(myObj, "UTF-8");
      
       //goes true every row one by one
       while (myReader.hasNextLine()) {
       //goes to next line
       String data = myReader.nextLine();
       
       //filters out empty rows and calculates amount of customers
       if (data.length() > 1) {
           amountcustomers++;
       }
           
       }//while

       //compares expected amount and actual amount of customers
        assertEquals(expResult, amountcustomers);
        myReader.close();
        //lets the user know the amount of customers is correct
        System.out.println("Amount of customers is right.");
        
    }//testCustomer
    
    public void testCustomerData() throws FileNotFoundException{
    
      //new 2D array for the expected customer values
      ArrayList<ArrayList<String>> expResult = new ArrayList<ArrayList<String>>();
      //new arraylist for the lines
      ArrayList<String> expline= new ArrayList<String>();
      //new array for the values 
      String[] expitems={"Karvinen","4356","1.27","6"};
      //add the values to the line in order
      expline.add(expitems[0]);
      expline.add(expitems[1]);
      expline.add(expitems[2]);
      expline.add(expitems[3]);
      //add the line to the 2D array
      expResult.add(expline);
      
                
      //new 2D array for the customer values
      ArrayList<ArrayList<String>> prospects = new ArrayList<ArrayList<String>>(); 
      //opens and imports all data from file.
       File myObj = new File("src/main/resources/prospects.txt");
       Scanner myReader = new Scanner(myObj, "UTF-8");
      
       //goes true every row one by one
       while (myReader.hasNextLine()) {
       //goes to next line
       String data = myReader.nextLine();
       
       //filters out empty rows
       if (data.length() > 1) {
           
           //filter extra from the customer data
            if (data.startsWith("\"")) {
                data = data.replace("\"","");
                data = data.replaceFirst("(?:,)+", " ");
            }
            
        //new array for the lines
        ArrayList<String> line = new ArrayList<String>();
        //splits customer data to different posisions in array 
        String[] items = data.split(",");
        
        //adds all values to line 
        for (int i = 0; i < items.length; i++) {
            line.add(items[i]);
        }
        
        //adds line to 2D array
        prospects.add(line);
        //clear out the 'items' array
        Arrays.fill(items, null);
        
       }//if
       
      }//while
        
       //compares expected and actual customer data
       assertEquals(expResult.get(0), prospects.get(2));
       myReader.close();
       //lets the user know the customer data is correct
       System.out.println("Customer data is correct.");
       
       
    }//testCustomerData
    
    
    
    public void testFormula(){
        
        // expected montly payment
        double expResult=56.6701774597168; 
        
        //the amount of total loan
        Float total_loan = Float.valueOf("1300.55");
        //the amount of intrest
        Float interest = Float.valueOf("8.67");
        //the amount of years customers will pay 
        Float years = Float.valueOf("2");
        //years to months
        Float months = years * 12;
        //the monthyl intrest
        Float monthly_interest = (interest / months)/100;
        //result in 2 deciamls
        Float result = 1.0f;

        
        while (months != 0) {
            result = result * (1 + monthly_interest);
            // power will get reduced after
            // each multiplication
            months--;
            
        }//while

        //the monthly payment
        float monthlypayment = (total_loan*monthly_interest*result)/(result-1);

        //compares expected and actual monthly payment
        assertEquals(expResult, monthlypayment);
        //lets the user know the formula is working
        System.out.println("Formula is working.");
    
    }//testFormula
            
             
}//mortageplanTest
