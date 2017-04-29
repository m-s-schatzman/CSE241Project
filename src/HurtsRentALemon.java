import Model.DBConnection;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HurtsRentALemon {

    public static void main(String[] args){
        DBConnection.connect();
        MainView.execute();
        System.out.println("Hope you enjoyed your painfully, bad experience!");
        DBConnection.close();
    }
}
