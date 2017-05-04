package View;

import Model.Customer;
import Model.Location;
import Model.Rental;
import Model.Reservation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by MarkSchatzman on 4/28/17.
 */
public class CustomerView {
    public static void execute(){
        Scanner scan = new Scanner(System.in);
        System.out.println("************************************************************");
        System.out.println("* 1. Returning customer                                    *");
        System.out.println("* 2. New Customer                                          *");
        System.out.println("* 0. Go back                                               *");
        System.out.println("************************************************************");
        boolean valid = false;
        String input;
        while(!valid) {
            input = scan.nextLine();
            if(input.equals("1")) {
                valid = true;
                existingCustomer("");
            }
            else if (input.equals("2")) {
                valid = true;
                newCustomer();
            } else if (input.equals("0")) {
                valid = true;
                MainView.execute();
            }
            else{
                System.out.println("Oops. Improper input. Try 'returning customer', 'new customer', " +
                        "or 'back':");
            }
        }

    }

    private static void newCustomer() {
        Scanner scan = new Scanner(System.in);
        String name;
        String street;
        String city;
        String region;
        scan.useDelimiter("\n");
        System.out.print("Please enter your full name:\n");
        name = scan.nextLine();
        System.out.print("Please enter your street:\n");
        street = scan.nextLine();
        System.out.print("Please enter your city:\n");
        city = scan.nextLine();
        System.out.print("Please enter your state/region:\n");
        region = scan.nextLine();
        System.out.println("Your name is " + name + ", and your address is " + street + " " +
                city + ", " + region);
        System.out.println("Is this correct? yes/no.");
        String response = scan.next();
        boolean ready = false;
        if (response.equals("no")) {
            while (!ready) {
                System.out.println("What would you like to change?");
                System.out.println("1: " + name);
                System.out.println("2: " + street);
                System.out.println("3: " + city);
                System.out.println("4: " + region);
                response = scan.next();
                switch (response) {
                    case "1":
                        System.out.print("Please enter your full name: ");
                        name = scan.next();
                        break;
                    case "2":
                        System.out.println("Please enter your street: ");
                        street = scan.next();
                        break;
                    case "3":
                        System.out.println("Please enter your city: ");
                        city = scan.next();
                        break;
                    case "4":
                        System.out.println("Please enter your state/region: ");
                        region = scan.next();
                        break;
                }
                System.out.println("Your name is " + name + ", and your address is " + street + " " +
                        city + ", " + region);
                System.out.println("Is this correct? (yes/no)");
                response = scan.next();
                if (response.equals("yes"))
                    ready = true;
            }
            execute();
        }
        Customer customer = new Customer(name, street, city, region);
        customer.insert();
        String id = Customer.getCustId(customer);
        System.out.println("Welcome " + name + "! Your customer id is " + id + ". " + "Please" +
                " remember this for future logins.");
        System.out.println("Would you like to make a vehicle reservation? (yes/no)");
        response = scan.next();
        if (response.equals("yes")) {
            newReservation(id);
        }
    }

    private static void existingCustomer(String id) {
        Scanner scan = new Scanner(System.in);
        String cust_id = "";
        if(id.equals("")) {
            System.out.println("Please enter your HRAL customer id. If you do not remember it, type '0' and consult HRAL management.");
            String choice = scan.next();
            String name;
            boolean valid = false;
            while (!valid) {
                if (choice.equals("0"))
                    MainView.execute();
                else {
                    cust_id = Customer.getCustId(choice);
                    if (!cust_id.equals("")) {
                        valid = true;
                        name = Customer.getCustName(cust_id);
                        System.out.println("Welcome back " + name + "!");
                    }
                }
            }
        }
        String choice;
        boolean valid = false;
        while(!valid){
            System.out.println("************************************************************");
            System.out.println("* 1. Create Reservation                                    *");
            System.out.println("* 2. See your rental history                               *");
            System.out.println("* 3. See your reservation history                          *");
            System.out.println("* 0. Go back                                               *");
            System.out.println("************************************************************");
            choice = scan.next();
            if(choice.equals("1")) {
                newReservation(cust_id);
                valid = true;
            }
            else if(choice.equals("2")){
                Rental.showCustomerHistory(cust_id);
            }
            else if(choice.equals("3")){
                Reservation.showCustomerHistory(cust_id);
            }
            else if(choice.equals("0")){
                execute();
            }
        }
    }

    private static void newReservation(String id){
        Scanner scan = new Scanner(System.in);
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
        System.out.println("When would you like to pick up your vehicle? Enter date (yyyy/mm/dd):");
        String pickup = scan.next();
        while(!dateIsValid(pickup)){
            pickup = scan.next();
        }
        System.out.println("How many days would you like to have your vehicle for?");
        boolean validInt = false;
        int numDays = 0;
        while(!validInt) {
            try {
                numDays = scan.nextInt();
                validInt = true;
            } catch (java.util.InputMismatchException ex) {
                System.out.println("Please enter an integer for number of days!");
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(pickup));
        }
        catch(java.text.ParseException ex){
            System.out.println(ex.getMessage());
        }
        c.add(Calendar.DATE, numDays);  // number of days to add
        String dropoff = sdf.format(c.getTime());
        Reservation reservation = new Reservation(id, pickup, dropoff, vehChoice);
        reservation.insert();
        System.out.println("Reservation created!");
        existingCustomer(id);
    }

    public static boolean dateIsValid(String dateToValidate){

        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
        sdf.setLenient(false);

        try {

            Date date = sdf.parse(dateToValidate);

        } catch (java.text.ParseException ex) {

            System.out.println("Please enter date in the following format: (yyyy/mm/dd)");
            return false;
        }

        return true;
    }


}
