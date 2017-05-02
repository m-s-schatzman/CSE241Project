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
