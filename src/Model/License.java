package Model;

/**
 * Created by MarkSchatzman on 4/27/17.
 */
public class License {
    private String id;
    private String dob;
    private String exp;

    public License(String id, String dob, String exp){
        this.id = id;
        this.dob = dob;
        this.exp = exp;
    }

    public void insert(int cust_id) {
        DBConnection.insertTuple("insert into license(lid, id, dob, exp) values('" + getId() + "', '" + cust_id +
                "', to_date('" + getDob() + "', 'yyyy/mm/dd'), to_date('" + getExp() + "', 'yyyy/mm/dd'))");
    }

    public String getId() {
        return id;
    }


    public String getDob() {
        return dob;
    }

    public String getExp() {
        return exp;
    }
}
