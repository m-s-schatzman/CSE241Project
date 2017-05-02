import Model.DBConnection;
import View.MainView;

public class HurtsRentALemon {

    public static void main(String[] args){
        DBConnection.connect();
        MainView.execute();
        System.out.println("Hope you enjoyed your painfully, bad experience!");
        DBConnection.close();
    }
}
