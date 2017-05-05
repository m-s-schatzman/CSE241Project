package Model;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by MarkSchatzman on 4/26/17.
 */
public class Vehicle {
    private double rate;
    private String make;
    private String model;
    private String year;
    private String type;
    private int odometer;
    private int location;
    private int available;

    public Vehicle(String make, String model, String year, String type,
                   int odometer, int location){
        rate = ThreadLocalRandom.current().nextDouble(200, 3000);
        rate = Math.round(rate*100)/100.0d;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
        this.odometer = odometer;
        this.location = location;
        this.available = 1;
    }

    public Vehicle(String make, String model, String year, String type,
                   int odometer, int location, double rate){
        this.rate = rate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
        this.odometer = odometer;
        this.location = location;
        this.available = 1;
    }

    public void insert(int location) {
        DBConnection.insertTuple("insert into vehicle(rate, make, model, year, type, odometer, loc_id, available) values('" + getRate() + "', '" + getMake() + "', '" + getModel() + "', '" +
                getYear() + "', '" + getType() + "', '" + getOdometer() + "', '" + location + "', '" + getAvailable() + "')");
    }

    public double getRate() {
        return rate;
    }

    public static String getRateFromDB(String vin) {
        ResultSet rs = DBConnection.getTuple("select rate from vehicle where vin = " + vin);
        String vinReturn = "";
        try {
            if (rs.next()) {
                vinReturn = rs.getString("rate");
            }
        }
        catch(java.sql.SQLException ex){
            ex.getMessage();
        }
        return vinReturn;
    }

    public static void listAll(){
        System.out.println();
        String city;
        String type;
        String year;
        String make;
        String model;
        String odometer;
        String rate;
        String region;
        String out;
        ResultSet rs = DBConnection.getTuple("select city, region, type, year, make, model, odometer, rate, available\n" +
                "from vehicle natural join location");
        try {
            if (!rs.isBeforeFirst()) {
                System.out.println("Uh oh.. don't know where all our cars went :(.");
                return;
            }
            System.out.printf("%-6s%-31s%-21s%-5s%-21s%-21s%-11s%-8s%-17s\n", "Count", "Location", "Type", "Year", "Make", "Model",
                    "Odometer", "Rate", "Currently Rented");
            System.out.printf("%-6s%-31s%-21s%-5s%-21s%-21s%-11s%-8s%-17s\n", "-----", "------------------------------", "--------------------", "----",
                    "--------------------", "--------------------", "----------", "-------", "----------------");
            int i = 1;
            while(rs.next()){
                city = rs.getString("city");
                region = rs.getString("region");
                type = rs.getString("type");
                year = rs.getString("year");
                make = rs.getString("make");
                model = rs.getString("model");
                odometer = rs.getString("odometer");
                rate = rs.getString("rate");
                out = rs.getString("available");
                if(out.equals("1"))
                    out = "No";
                else
                    out = "Yes";
                System.out.printf("%-6s%-31s%-21s%-5s%-21s%-21s%-11s%-8s%-17s\n", i++, city + ", " + region, type, year, make, model, odometer, rate, out);
            }
        }
        catch(java.sql.SQLException ex){
            ex.getMessage();
        }
        System.out.println();
    }


    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public int getOdometer() {
        return odometer;
    }

    public int getAvailable() {
        return available;
    }

    public static void setUnavailable(int vin) {
        DBConnection.updateTuple("update vehicle set available = 0 where vin = " + vin);
    }

}
