package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MarkSchatzman on 4/27/17.
 */
public class Location {
    private String street;
    private String city;
    private String region;
    public static int numLocations = 0;

    public Location(String street, String city, String region){
        this.street = street;
        this.city = city;
        this.region = region;
        numLocations++;
    }

    public void insert() {
        DBConnection.insertTuple("insert into location(street, city, region) values('" + getStreet() +
                "', '" + getCity() + "', '" + getRegion() + "')");
    }

    public static void showLocations() {
        System.out.println();
        ResultSet rs = DBConnection.getTuple("select * from location");
        String street;
        String city;
        String region;
        System.out.printf("%-6s%-31s%-31s%-31s\n", "Count", "Street", "City", "Location");
        System.out.printf("%-6s%-31s%-31s%-31s\n", "-----", "-------------------------", "------------------------",
                "------------------------");
        int i = 1;
        try {
            while (rs.next()) {
                street = rs.getString("street");
                city = rs.getString("city");
                region = rs.getString("region");
                System.out.printf("%-6s%-31s%-31s%-31s\n", i++, street, city, region);
            }
        } catch (java.sql.SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println();
    }

    public static boolean showVehicles(String loc_id){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        ArrayList<String> vins = new ArrayList<>();
        ResultSet rs = DBConnection.getTuple("select vin, year, type, make, model " +
                "from vehicle natural join location" +
                " where loc_id = " + loc_id + "and available = 1");
        String type;
        String year;
        String make;
        String model;
        String vin;
        try {
            while (rs.next()) {
                type = rs.getString("type");
                year = rs.getString("year");
                make = rs.getString("make");
                model = rs.getString("model");
                vin = rs.getString("vin");
                vins.add(vin);
                Vehicle vehicle = new Vehicle(make, model, year, type, 0, 0);
                vehicles.add(vehicle);
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        if(vehicles.isEmpty()){
            return false;
        }
        System.out.printf("%-4s%-21s%-5s%-21s%-21s\n", "VIN", "Type", "Year", "Make", "Model");
        System.out.printf("%-4s%-21s%-5s%-21s%-21s\n", "---", "--------------------", "----", "--------------------",
                "--------------------");
        Vehicle currVehic;
        for(int i = 0; i < vehicles.size(); i++){
            currVehic = vehicles.get(i);
            System.out.printf("%-4s%-21s%-5s%-21s%-21s\n", vins.get(i), currVehic.getType(), currVehic.getYear(), currVehic.getMake(), currVehic.getModel());
        }
        return true;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }
}
