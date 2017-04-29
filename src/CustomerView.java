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
                " HRAL customer ID. Else type 'new customer'. If you would like" +
                " to go back, type 'back'");
            String input = scan.nextLine();
            if (input.equals("new customer")) {
                newCustomer();
            }
            else if(input.equals("back")){
                MainView.execute();
            }
    }

    private static void newCustomer(){
        System.out.println("Please enter your name, street address, city, region" + " as indicated with commas.");
        String line = scan.nextLine();
        String[] arr = line.split(", ");
        String name = arr[0];
        String street = arr[1];
        String city = arr[2];
        String region = arr[3];
        Customer customer = new Customer(name, street, city, region);
        customer.insert();
        System.out.println("Welcome " + name + "!");
        System.out.println("Would you like to make a vehicle reservation?");
        String response;
        response = scan.next();
        if(response.equals("yes")) {
            newReservation();
        }
    }

    private static void newReservation(){
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
    }

}
