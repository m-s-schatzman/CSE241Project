package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class DBConnection {
    private static Connection con;
    private static Statement s;

    public static void connect() {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Oracle user id:");
        String username = scan.next();
        System.out.println("Enter Oracle password for " + username + ":");
        String password = scan.next();

        boolean success = false;
        while (!success) {
            try {
                con = DriverManager.getConnection("jdbc:oracle:thin:@edgar0.cse.lehigh.edu:1521:cse241", username, password);
                success = true;
            } catch (java.sql.SQLException ex) {
                System.out.println("enter Oracle user id:");
                username = scan.next();
                System.out.println("enter Oracle password for " + username + ":");
                password = scan.next();
            }
        }
    }


    public static void insertTuple(String query){
        try {
            s = con.createStatement();

            //System.out.println(query);

            // execute insert SQL stetement
            s.executeUpdate(query);

            //System.out.println("Record is inserted into DBUSER table!");

        } catch (java.sql.SQLException e) {

            System.out.println(e.getMessage());

        }
    }

    public static ResultSet getTuple(String query){
        ResultSet rs = null;
        try {
            s = con.createStatement();

            //System.out.println(query);

            // execute insert SQL stetement
            rs = s.executeQuery(query);

            //System.out.println("Record is inserted into DBUSER table!");

        } catch (java.sql.SQLException e) {

            System.out.println(e.getMessage());
        }
        return rs;
    }

    public static void updateTuple(String query){
        try {
            s = con.createStatement();


            // execute insert SQL stetement
            s.executeUpdate(query);


        } catch (java.sql.SQLException e) {

            System.out.println(e.getMessage());

        }
    }


    public static void close(){
        try {
            con.close();
        }
        catch(java.sql.SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static Connection getCon() {
        return con;
    }
}
