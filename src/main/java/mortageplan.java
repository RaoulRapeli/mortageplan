/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author raoul
 */

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.text.DecimalFormat;

public class mortageplan {

    
    
    public static void main(String[] args) {
     
     //new 2D array for the customer values
     ArrayList<ArrayList<String>> prospects = new ArrayList<ArrayList<String>>(); 
     
     //insert counters for the 2D array positions
     int rows=0;
     int outerCounter=0;
     
    try {
      
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
        //adds one to row count
        rows++;
        //clear out the 'items' array
        Arrays.fill(items, null);
       }//if
       
      }//while
       
       //goes true all customer data, calculates monthly payment ant prints out the information
       while(outerCounter<rows-1){
           //adds one to position of array
            outerCounter++;
            //customer name
            String name = String.valueOf(prospects.get(outerCounter).get(0));
            //the amount of total loan
            Float total_loan = Float.valueOf(prospects.get(outerCounter).get(1));
            //the amount of intrest
            Float interest = Float.valueOf(prospects.get(outerCounter).get(2));
            //the years customers will pay 
            Float years = Float.valueOf(prospects.get(outerCounter).get(3));
            //years to months
            Float months = years * 12;
            
            //counts to the years
            float counter=1;
            //intrest from % to decimal
            float total_interest=(1+(interest/100));
            //for each year
           while (counter < years) {
                total_interest=total_interest*total_interest;
                // power will get reduced after
                // each multiplication
                counter++;
            }//while
            
           //total payment
           float total_payment= total_loan*total_interest;
          
            //the monthly payment
            float monthlypayment = total_payment/months;
            //makes toatl lones and monthly payment two deciaml values
            DecimalFormat f = new DecimalFormat("##.00");
            //makes years integer
            DecimalFormat e = new DecimalFormat("##");
   
            //prints out all information of customer
            System.out.println("Prospect " + outerCounter + ": " 
                    +  name
                    + " wants to borrow " 
                    + (f.format(total_loan))
                    + " € for a period of "
                    + (e.format(years))
                    + " years and pay "
                    + (f.format(monthlypayment))
                    + "€ each month ");
        
       }//While
       
      myReader.close();
    }//try  
    
    //gives error message if something whent wrong
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }//catch
    
  }//main
    
}//mortageplan
