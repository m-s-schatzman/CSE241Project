package Model;

/**
 * Created by MarkSchatzman on 4/26/17.
 */
public class Vehicle {
    private String make;
    private String model;
    private String year;
    private String type;
    private int odometer;
    private int location;
    private int available;

    public Vehicle(String make, String model, String year, String type,
                   int odometer, int location){
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
        this.odometer = odometer;
        this.location = location;
        this.available = 1;
    }

    public void insert(int location) {
        DBConnection.insertTuple("insert into vehicle(make, model, year, type, odometer, loc_id, available) values('" + getMake() + "', '" + getModel() + "', '" +
                getYear() + "', '" + getType() + "', '" + getOdometer() + "', '" + location + "', '" + getAvailable() + "')");
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
}
