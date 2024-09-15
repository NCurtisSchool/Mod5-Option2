/*
    Program NameL Module 5 Critical Thinking Option 2
    Author: Nathan Curtis
    Date: (9/15/2024)
    -----------------------------------------------------
    PseudoCode:

    Add needed imports

    Main:
        Array for Months
        Array for MonthlyTemps
        inLoop = True
        
        while loop inLoop = True
            Ask and get users input for the mounth or to type 'year' for all months, the average and the min and max. Type quit to end
            input.toLowerCase().trim()
            If input = 'quit' then make inloop false
            else if input = 'year' send to yearData
            else
                for i in Months find month = input
                use i to call temp
                print
        
        Close Program
    
    Yeardata(needs both arrays);
        calculate emp average
        find minimum temp and store index
        find max and store index

        print average
        print max
        print min
        create empty space
        print "Showing Yearly results"
        print jan to december, format nicely with a new line for each data point
    
    ------------------------------------------------------------------
    Citation:
        Salas, E. B. (2024, August 27). U.S. average temperature by month 2024. Average monthly temperature in the United States from January 2020 to July 2024. https://www.statista.com/statistics/513628/monthly-average-temperature-in-the-us-fahrenheit/ 
*/

import java.util.Scanner; // Allow user input
import java.text.DecimalFormat; // Allow specific decimal point

public class main {
    public static void main(String[] args) {
        String[] MonthArray = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}; // Define Array + Fill with months
        String[] ChoiceArray = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December", "Quit", "Year"};
        Double[] TemperatureArray = new Double[] {35.53, 36.13, 46.08, 50.86, 60.89, 70.27, 75.63, 74.68, 65.89, 54.28, 46.13, 35.71}; // Define Array + Fill with Temperatures. Data by Salas recording US average temps. Used 2020.
        boolean inLoop = true; // Create condion for loop
        
        int failCounter = 0; // Used later to check if user input matches with the months.

        Scanner myObj = new Scanner(System.in); // Define and open scanner object
        loop: while (inLoop = true) {
            String userInput = "";
            System.out.println("Please enter the month of whos temperature you want \nType \"Year\" to get data about the entire year\nType \"Quit\" to Exit the program");
            System.out.println("User Input: ");
            userInput = myObj.nextLine(); // Gets user input while also making it consistently lowercase and have no spaces at either end
            userInput = userInput.toLowerCase().toString();
            loopTwo: for (int i = 0; i < 14; i++) { // Cycle 12 times. 1 for each month
                if (userInput.equals(ChoiceArray[i].toLowerCase())) { // check if input matches month araay. Note use toLoweCase() so that there is no conflict but maintains the formaat of original list
                    if (i == 12) {
                        break loop;
                    } else if (i == 13) {
                        yearData(MonthArray, TemperatureArray); // Do year data
                        failCounter = 0;
                        break loopTwo;
                    }
                    else {
                    failCounter = 0; // reset fail counter if true
                    System.out.println("For the month of " + MonthArray[i] + " the average temperature in the USA was " + TemperatureArray[i] + " F\n\n\n\n"); // Print results of the specific month and temp if true
                    break loopTwo;
                    }
                } else { // Every time the condition is not met, add 1 to counter.
                    failCounter += 1;
                }
            }
            if (failCounter == 14) { //if no success in finding a match, reset fail counter and have user repeat process
                failCounter = 0;
                System.out.println("User input is invalid. Try again.\n\n\n\n");
            }
        
        }
        myObj.close(); // Close Scanner
    }

    public static void yearData(String[] months, Double[] temps) {
        String minMonth = "", maxMonth = ""; // MUST ADD THE = ""
        double min = 0, max = 0, total = 0, average = 0;
        for (int i = 0; i < 12; i++) {
            total += temps[i]; // tracks total temperature for calculations
            if (temps[i] > max) { // Checks if a temerature is greater than previous
                max = temps[i]; // Adds temperature to max varaibale
                maxMonth = months[i]; // Keeps location to determin month.
            }
            if (temps[i] < min || i == 0) { // Checks if grade is less than previous temps, or if this is the first number added
                min = temps[i]; // Adds to min variable
                minMonth = months[i]; // Keeps location to determin month.
            }
        }
        average = total / 12; // Averages total temperature.

        DecimalFormat df = new DecimalFormat("0.00"); // Formats 2 decimals for easy reading

        System.out.println("\n\n\n");
        System.out.println("The average temperature for the year is: " + df.format(average));
        System.out.println("The lowest temperature was in " + minMonth + " and was " + min + " F");
        System.out.println("The highest temperatur was in " + maxMonth + " and was " + max + " F");
        System.out.println("The data for the year");
        for (int i = 0; i < 12; i++) {
            System.out.printf("%-20s%-8s\n", months[i], temps[i]);
        }
    }
}