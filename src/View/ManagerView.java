package View;

import Model.Rental;

import java.util.Scanner;

public class ManagerView {

    public static void execute(){
        Scanner scan = new Scanner(System.in);
        boolean done = false;
        while(!done) {
            System.out.println("************************************************************");
            System.out.println("* Please select from the following:                        *");
            System.out.println("* 1. List all rentals.                                  *");
            System.out.println("* 2. List all reservations                              *");
            System.out.println("* 3. List of total vehicle inventory.                      *");
            System.out.println("* 4. Get list of vehicles at specific location.            *");
            System.out.println("* 5. List of all customers.                                *");
            System.out.println("* 0. Go back                                               *");
            System.out.println("************************************************************");
            String choice = scan.next();
            if (choice.equals("1")) {
                Rental.listAll();
            } else if (choice.equals("0")) {
                done = true;
                MainView.execute();
            }
        }

    }

}
