package Model;

import java.sql.ResultSet;

/**
 * Created by MarkSchatzman on 4/29/17.
 */
public class Reservation {
    private String cust_id;
    private String startDate;
    private String endDate;
    private String vin;

    public Reservation(String cust_id, String startDate, String endDate, String vin){
        this.cust_id = cust_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vin = vin;
    }

    public void insert() {
        DBConnection.insertTuple("insert into reservation(id, start_date, end_date, vin) values('" + getCust_id() +
                "', '" + getStartDate() + "', '" + getEndDate() + "', '" + getVin() + "')");
    }

    public String getCust_id() {
        return cust_id;
    }


    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getVin() {
        return vin;
    }
}
