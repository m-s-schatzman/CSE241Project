package View;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by MarkSchatzman on 4/29/17.
 */
public class MainView {
    public static void execute() {
        System.out.println("Welcome to Hurtz-Rent-A-Lemon!\n");
        System.out.println("Please select the following which best suits your visit: ");
        System.out.println("1: Customer");
        System.out.println("2: Employee");
        System.out.println("3: Manager");
        System.out.println("If you wish to exit this application. Type 0");
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String response;
        while (!exit) {
            try {
                response = scanner.next();
                if (response.equals("1")) {
                    CustomerView.execute();
                    exit = true;
                }
                else if(response.equals("2")){
                    EmployeeView.execute();
                    exit = true;
                }
                else if (response.equals("0")) {
                    exit = true;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter 1 for Customer, 2 for Employee," +
                        " and 3 for manager. Exit with 0.");
            }
        }
    }
}
