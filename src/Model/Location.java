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

    public static void showLocations(){
        ArrayList<Location> locations = new ArrayList<>();
        ResultSet rs = DBConnection.getTuple("select * from location");
        String street;
        String city;
        String region;
        int index = 0;
        try {
            while (rs.next()) {
                street = rs.getString("street");
                city = rs.getString("city");
                region = rs.getString("region");
                Location location = new Location(street, city, region);
                locations.add(location);
            }
        }
        catch(java.sql.SQLException ex){
            System.out.println(ex.getMessage());
        }
        Location currLoc;
        for(int i = 0; i < locations.size(); i++){
            currLoc = locations.get(i);
            System.out.printf("%-5s%-25s%-25s%-25s\n", Integer.toString(i + 1) + ":", currLoc.getStreet(), currLoc.getCity(), currLoc.getRegion());
        }
    }

    public static boolean showVehicles(String loc_id){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        ArrayList<String> vins = new ArrayList<>();
        ResultSet rs = DBConnection.getTuple("select vin, year, type, make, model " +
                "from vehicle natural join location" +
                " where loc_id = " + loc_id);
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
        Vehicle currVehic;
        for(int i = 0; i < vehicles.size(); i++){
            currVehic = vehicles.get(i);
            System.out.printf("%-5s%-20s%-20s%-20s%-20s\n", vins.get(i) + ":", currVehic.getType(), currVehic.getYear(), currVehic.getMake(), currVehic.getModel());
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
