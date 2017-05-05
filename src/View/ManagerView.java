package View;

import Model.Location;
import Model.Rental;
import Model.Reservation;
import Model.Vehicle;

import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

public class ManagerView {

    public static void execute() {
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            System.out.println("************************************************************");
            System.out.println("* Please select from the following:                        *");
            System.out.println("* 1. List all rentals.                                     *");
            System.out.println("* 2. List all reservations                                 *");
            System.out.println("* 3. List of total vehicle inventory.                      *");
            System.out.println("* 4. Add vehicles to fleet.                                *");
            System.out.println("* 0. Go back                                               *");
            System.out.println("************************************************************");
            String choice = scan.next();
            if (choice.equals("1")) {
                Rental.listAll();
            } else if (choice.equals("2")) {
                Reservation.listAll();
            } else if (choice.equals("3")) {
                Vehicle.listAll();
            } else if (choice.equals("4")) {
                addVehicles();
            } else if (choice.equals("0")) {
                done = true;
                MainView.execute();
            }
        }

    }

    public static void addVehicles() {
        boolean done = false;
        while (!done) {
            Vehicle vehicle;
            String make;
            String model;
            String year;
            String type;
            String odometer;
            String rate;
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter make:");
            make = scan.nextLine();
            System.out.println("Enter model:");
            model = scan.nextLine();
            System.out.println("Enter year:");
            year = scan.nextLine();
            System.out.println("Enter type:");
            type = scan.nextLine();
            System.out.println("Enter odometer:");
            odometer = scan.nextLine();
            System.out.println("Enter rate:");
            rate = scan.nextLine();
            System.out.println(year + " " + make + " " + model + ", " + type + ". " + odometer + " miles. " + "Rate: $" + rate);
            System.out.println("Is this correct? (yes/no)");
            String response = scan.nextLine();
            if (response.equals("no")) {
                boolean ready = false;
                if (response.equals("no")) {
                    while (!ready) {
                        System.out.println("What would you like to change?");
                        System.out.println("1: " + make);
                        System.out.println("2: " + model);
                        System.out.println("3: " + year);
                        System.out.println("4: " + type);
                        System.out.println("5: " + odometer);
                        System.out.println("6: " + rate);
                        response = scan.next();
                        switch (response) {
                            case "1":
                                System.out.print("Enter make: ");
                                make = scan.nextLine();
                                break;
                            case "2":
                                System.out.println("Enter model: ");
                                model = scan.nextLine();
                                break;
                            case "3":
                                System.out.println("Enter year: ");
                                year = scan.nextLine();
                                break;
                            case "4":
                                System.out.println("Enter type: ");
                                type = scan.nextLine();
                                break;
                            case "5":
                                System.out.println("Enter odometer: ");
                                odometer = scan.next();
                            case "6":
                                System.out.println("Enter rate: ");
                                rate = scan.next();
                        }
                        System.out.println(year + " " + make + " " + model + ", " + type + ". " + odometer + " miles. " + "Rate: $" + rate);
                        System.out.println("Is this correct? (yes/no)");
                        response = scan.next();
                        if (response.equals("yes"))
                            ready = true;
                    }
                }
            }
            Location.showLocations();
            System.out.println("Select the location id where you would like to add this vehicle.");
            int loc_id = scan.nextInt();
            vehicle = new Vehicle(make, model, year, type, Integer.parseInt(odometer), loc_id, Double.parseDouble(rate));
            vehicle.insert(loc_id);
            System.out.println("Vehicle added!");
            System.out.println("Would you like to add another vehicle? (yes/no)");
            String choice = scan.next();
            if (choice.equals("no"))
                done = true;
        }
    }
}