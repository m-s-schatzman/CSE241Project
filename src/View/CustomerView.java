package View;

import Model.Customer;
import Model.Location;

import java.util.Scanner;

/**
 * Created by MarkSchatzman on 4/28/17.
 */
public class CustomerView {
    private static Scanner scan = new Scanner(System.in);
    public static void execute(){
        System.out.println("If you are a returning customer please enter your" +
                " type 'existing customer'. Else type 'new customer'. If you would like" +
                " to go back, type 'back'");
        boolean valid = false;
        String input;
        while(!valid) {
            input = scan.nextLine();
            if(input.equals("returning customer")) {
                valid = true;
                existingCustomer();
            }
            else if (input.equals("new customer")) {
                valid = true;
                newCustomer();
            } else if (input.equals("back")) {
                valid = true;
                MainView.execute();
            }
            else{
                System.out.println("Oops. Improper input. Try 'existing customer', 'new customer', " +
                        "or 'back'.");
            }
        }

    }

    private static void newCustomer(){
        boolean correct = false;
        String name = "";
        String street = "";
        String city = "";
        String region = "";
        scan.useDelimiter("\n");
        while(!correct) {
            System.out.println("Please enter your full name:");
            name = scan.nextLine();
            System.out.println("Please enter your street:");
            street = scan.nextLine();
            System.out.println("Please enter your city:");
            city = scan.nextLine();
            System.out.println("Please enter your state/region:");
            region = scan.nextLine();
            System.out.println("Your name is " + name + ", and your address is " + street + ", " +
                    city + ", " + region);
            System.out.println("Is this correct? yes/no.");
            String response = scan.nextLine();
            if(response.equals("yes")){
                correct = true;
            }
            else {
                System.out.println("Okay, no problem. Let's try again");
            }
        }
        Customer customer = new Customer(name, street, city, region);
        customer.insert();
        System.out.println("Welcome " + name + "!");
        System.out.println("Would you like to make a vehicle reservation?");
        String response;
        response = scan.next();
        if(response.equals("yes")) {
            newReservation(customer);
        }
    }

    private static void existingCustomer(){

    }

    private static void newReservation(Customer customer){
        Location.showLocations();
        System.out.println("Select the id from the above locations where you would like" +
                " to rent a painfully, bad vehicle from: ");
        String locChoice = scan.next();
        while(!Location.showVehicles(locChoice))
        {
            System.out.println("Sorry there are currently no vehicles available at this location. Try another location id.");
            locChoice = scan.next();
        }
        System.out.println("The vehicles listed above are available at this location" +
                " please select the id of the vehicle that you would like to reserve.");
        String vehChoice = scan.next();
    }

}
