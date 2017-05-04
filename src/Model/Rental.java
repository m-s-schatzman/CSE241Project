package Model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MarkSchatzman on 4/30/17.
 */
public class Rental {
    private String custId;
    private String locId;
    private String vin;
    private double rate;
    private int numDays;
    private String chargeId;
    private double total;

    public Rental(String custId, String locId, String vin, double rate,
                  int numDays){
        this.custId = custId;
        this.locId = locId;
        this.vin = vin;
        this.rate = rate;
        this.numDays = numDays;
    }

    public void insert() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss");
        String stringDate = sdf.format(now);
        DBConnection.insertTuple("insert into rental(id, locid, vin, rate, pickup, dropoff)" +
                " values(" + getCustId() + ", " + getLocId() + ", " + getVin() + ", " + getRate()
                + ", " + "current_timestamp, " + "(select to_timestamp('" + stringDate +
                "'," + "'DD-Mon-YYYY HH24-MI-SS') + " + getNumDays() + " FROM dual))");
        System.out.println("insert into rental(id, locid, vin, rate, pickup, dropoff)" +
                " values(" + getCustId() + ", " + getLocId() + ", " + getVin() + ", " + getRate()
                + ", " + "current_timestamp, " + "(select to_timestamp('" + stringDate +
                "'," + "'DD-Mon-YYYY HH24-MI-SS') + " + getNumDays() + " FROM dual));");
    }

    public static void listAll(){
        String name;
        String year;
        String make;
        String model;
        String dtStart;
        String dtEnd;
        ResultSet rs = DBConnection.getTuple("select name, year, make, model, pickup, dropoff\n" +
                "from customer natural join rental natural join vehicle");
        try {
            if (!rs.isBeforeFirst()) {
                System.out.println("There are no current rentals.");
                return;
            }
            System.out.printf("%-20s%5s%15s%15s%15s%15s\n", "Name", "Year", "Make", "Model", "Pickup Date", "Dropoff Date");
            System.out.printf("%-20s%5s%15s%15s%15s%15s\n", "--------------------", "-----", "---------------", "---------------",
                    "---------------", "---------------");
            int i = 1;
            while(rs.next()){
                name = rs.getString("name");
                year = rs.getString("year");
                make = rs.getString("make");
                model = rs.getString("model");
                dtStart = rs.getString("pickup");
                dtEnd = rs.getString("dropoff");
                System.out.printf("%-20s%5s%15s%15s%15s%15s\n", i++ + ": " + name, year, make, model, dtStart.split(" ")[0], dtEnd.split(" ")[0]);
            }
        }
        catch(java.sql.SQLException ex){
            ex.getMessage();
        }
    }

    public static void showCustomerHistory(String id){
        String pickup;
        String year;
        String make;
        String model;
        ResultSet rs = DBConnection.getTuple("select pickup, year, make, model from customer natural join vehicle natural join rental " +
                "where id = " + id);
        try {
            if (!rs.isBeforeFirst()) {
                System.out.println("You have not rented from us before. You're missing out on a painfully bad experience!");
                return;
            }
            System.out.printf("%-8s%-15s%-8s%-15s%-15s\n", "RentNo", "Start Date", "Year", "Make", "Model");
            System.out.printf("%-8s%-15s%-8s%-15s%-15s\n", "------", "----------", "----", "----", "-----");
            int i = 1;
            while (rs.next()) {
                pickup = rs.getString("pickup");
                year = rs.getString("year");
                make = rs.getString("make");
                model = rs.getString("model");
                System.out.printf("%-8s%-15s%-8s%-15s%-15s\n", i++ + ": ", pickup.split(" ")[0], year, make, model);
            }
        }
        catch(java.sql.SQLException ex){
            DBConnection.close();
        }
    }

    public String getCustId() {
        return custId;
    }

    public String getLocId() {
        return locId;
    }

    public String getVin() {
        return vin;
    }

    public double getRate() {
        return rate;
    }

    public int getNumDays() {
        return numDays;
    }
}
