package View;

import Model.*;
import sun.nio.ch.sctp.SctpNet;

import java.util.Scanner;

/**
 * Created by MarkSchatzman on 4/30/17.
 */
public class EmployeeView {
    private static String locId;

    public static void execute() {
        Scanner scan = new Scanner(System.in);
        Location.showLocations();
        System.out.println("Please select the location id from where you are working:");
        locId = scan.next();
        System.out.println("1. Create a rental for an existing customer.\n2. Create a rental for a new customer\n" +
                "0. Go back to home.");
        String response;
        boolean valid = false;
        while (!valid) {
            response = scan.next();
            if (response.equals("1")) {
                existingCustomerRental();
                valid = true;
            } else if (response.equals("2")) {
                newCustomerRental();
            } else if (response.equals("0")) {
                MainView.execute();
            }
        }
    }

    public static void home() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Create a rental for an existing customer.\n2. Create a rental for a new customer\n" +
                "0. Go back to home.");
        String response;
        boolean valid = false;
        while (!valid) {
            response = scan.nextLine();
            if (response.equals("1")) {
                existingCustomerRental();
                valid = true;
            } else if (response.equals("2")) {
                newCustomerRental();
            } else if (response.equals("0")) {
                MainView.execute();
            }
        }
    }

    public static void existingCustomerRental() {
        Scanner scan = new Scanner(System.in);
        String response;
        String custId;
        String vin;
        int numDays;
        boolean done = false;
        while(!done) {
            Customer.showCustomers();
            System.out.println("Select the customer id for who you wish to make a rental.");
            custId = scan.nextLine();
            Location.showVehicles(locId);
            System.out.println("Select the vehicle id that the customer would like to rent.");
            vin = scan.nextLine();
            System.out.println("How many days will the rental be?");
            numDays = scan.nextInt();
            Rental rental = new Rental(custId, locId, vin, Double.parseDouble(Vehicle.getRateFromDB(vin)), numDays);
            rental.insert();
            Vehicle.setUnavailable(Integer.parseInt(vin));
            System.out.println("Rental created!");
            System.out.println("Would you like to create another rental? yes/no");
            Scanner scanner = new Scanner(System.in);
            response  = scanner.nextLine();
            if(response.equals("no")) {
                done = true;
                EmployeeView.home();
            }

        }
    }

    public static void newCustomerRental(){
        Scanner scan = new Scanner(System.in);
        String vin;
        int numDays;
        System.out.println("Please enter the customer's 'name', 'street', 'city', 'region'" +
        " as indicated with commas.");
        String line = scan.nextLine();
        String[] custInfo = line.split(", ");
        Customer customer = new Customer(custInfo[0], custInfo[1], custInfo[2], custInfo[3]);
        customer.insert();
        System.out.println("Please enter " + custInfo[0] + "'s 'license number', 'date of birth', 'license exp date' " +
        "as indicated with commas.");
        String licLine = scan.nextLine();
        String[] custLisc = licLine.split(", ");
        License license = new License(custLisc[0], custLisc[1], custLisc[2]);
        int custId = Integer.parseInt(Customer.getCustId(customer));
        license.insert(custId);
        System.out.println("Select the vehicle id that the customer would like to rent.");
        vin = scan.nextLine();
        System.out.println("How many days will the rental be?");
        numDays = scan.nextInt();
        Rental rental = new Rental(Customer.getCustId(customer), locId, vin, Double.parseDouble(Vehicle.getRateFromDB(vin)), numDays);
        rental.insert();
        Vehicle.setUnavailable(Integer.parseInt(vin));
        System.out.println("Rental created!");
    }


}
