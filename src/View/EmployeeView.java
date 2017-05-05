package View;

import Model.*;

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
        System.out.println("************************************************************");
        System.out.println("* 1. Create a rental for an existing customer              *");
        System.out.println("* 2. Create a rental for a new customer                    *");
        System.out.println("* 0. Go back to home.                                      *");
        System.out.println("************************************************************");
        String response;
        boolean valid = false;
        while (!valid) {
            response = scan.next();
            if (response.equals("1")) {
                valid = true;
                existingCustomerRental();
            } else if (response.equals("2")) {
                valid = true;
                newCustomerRental();
            } else if (response.equals("0")) {
                valid = true;
                MainView.execute();
            }
        }
    }

    public static void home() {
        Scanner scan = new Scanner(System.in);
        System.out.println("************************************************************");
        System.out.println("* 1. Create a rental for an existing customer              *");
        System.out.println("* 2. Create a rental for a new customer                    *");
        System.out.println("* 0. Go back to home.                                      *");
        System.out.println("************************************************************");
        String response;
        boolean valid = false;
        while (!valid) {
            response = scan.nextLine();
            if (response.equals("1")) {
                existingCustomerRental();
                valid = true;
            } else if (response.equals("2")) {
                newCustomerRental();
                valid = true;
            } else if (response.equals("0")) {
                MainView.execute();
                valid = true;
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
        while (!done) {
            Customer.showCustomers();
            System.out.println("Select the customer id for who you wish to make a rental:");
            custId = scan.nextLine();
            if (!License.checkID(custId)) {
                System.out.println("License information required for " + Customer.getCustName(custId));
                takeLicenseInformation(custId);
            }
            Location.showVehicles(locId);
            System.out.println("Select the vehicle id that the customer would like to rent:");
            vin = scan.nextLine();
            System.out.println("How many days will the rental be?");
            numDays = scan.nextInt();
            Rental rental = new Rental(custId, locId, vin, Double.parseDouble(Vehicle.getRateFromDB(vin)), numDays);
            rental.insert();
            Vehicle.setUnavailable(Integer.parseInt(vin));
            System.out.println("Rental created!");
            System.out.println("Would you like to create another rental? yes/no");
            Scanner scanner = new Scanner(System.in);
            response = scanner.nextLine();
            if (response.equals("no")) {
                done = true;
                home();
            }

        }
    }

    public static void newCustomerRental() {
        Scanner scan = new Scanner(System.in);
        String vin;
        int numDays;
        System.out.println("Please enter the customer's 'name', 'street', 'city', 'region'" +
                " as indicated with commas.");
        String line = scan.nextLine();
        String[] custInfo = line.split(", ");
        Customer customer = new Customer(custInfo[0], custInfo[1], custInfo[2], custInfo[3]);
        customer.insert();
        System.out.println("Please enter " + custInfo[0] + "'s 'license number', 'date of birth (yyyy/mm/dd)', 'license exp date (yyyy/mm/dd)' " +
                "as indicated with commas and specified date formats.");
        String licLine = scan.nextLine();
        String[] custLisc = licLine.split(", ");
        License license = new License(custLisc[0], custLisc[1], custLisc[2]);
        int custId = Integer.parseInt(Customer.getCustId(customer));
        license.insert(custId);
        Location.showVehicles(locId);
        System.out.println("Select the vehicle id that the customer would like to rent.");
        vin = scan.nextLine();
        System.out.println("How many days will the rental be?");
        numDays = scan.nextInt();
        Rental rental = new Rental(Customer.getCustId(customer), locId, vin, Double.parseDouble(Vehicle.getRateFromDB(vin)), numDays);
        rental.insert();
        Vehicle.setUnavailable(Integer.parseInt(vin));
        System.out.println("Rental created!");
        home();
    }

    public static void takeLicenseInformation(String cust_id) {
        String lid;
        String dob;
        String exp;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter license number: ");
        lid = scan.nextLine();
        System.out.println("Enter date of birth (yyyy/mm/dd): ");
        dob = scan.nextLine();
        System.out.println("Enter expiration date (yyyy/mm/dd): ");
        exp = scan.nextLine();
        System.out.println("License number: " + lid + ", DOB: " + dob + ", EXP: " + exp);
        System.out.println("Is this correct? (yes/no)");
        String response = scan.nextLine();
        boolean ready = false;
        if (response.equals("no")) {
            while (!ready) {
                System.out.println("What would you like to change?");
                System.out.println("1: " + lid);
                System.out.println("2: " + dob);
                System.out.println("3: " + exp);
                response = scan.next();
                switch (response) {
                    case "1":
                        System.out.print("Enter license number: ");
                        lid = scan.next();
                        break;
                    case "2":
                        System.out.println("Enter date of birth (yyyy/mm/dd): ");
                        dob = scan.next();
                        break;
                    case "3":
                        System.out.println("Enter expiration date (yyyy/mm/dd): ");
                        exp = scan.next();
                        break;
                }
                System.out.println("License number: " + lid + ", DOB: " + dob + ", EXP: " + exp);
                System.out.println("Is this correct? (yes/no)");
                response = scan.next();
                if (response.equals("yes"))
                    ready = true;
            }

        }
    }
}
