package Model;

/**
 * Created by MarkSchatzman on 4/29/17.
 */
public class Reservation {
    private int cust_id;
    private String startDate;
    private String endDate;
    private int vin;

    public Reservation(int cust_id, String startDate, String endDate, int vin){
        this.cust_id = cust_id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vin = vin;
    }

    public void insert() {
        DBConnection.insertTuple("insert into reservation(id, start_date, end_date, vin) values('" + getCust_id() +
                "', '" + getStartDate() + "', '" + getEndDate() + "', '" + getVin() + "')");
    }

    public int getCust_id() {
        return cust_id;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getVin() {
        return vin;
    }
}
