package Model;

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
