import Model.DBConnection;
import View.MainView;

public class HurtsRentALemon {

    public static void main(String[] args){
        DBConnection.connect();
        MainView.execute();
    }
}
