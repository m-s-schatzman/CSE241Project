package Model;

/**
 * Created by MarkSchatzman on 4/27/17.
 */
public class CreditCard {
    private String number;
    private String cvv;
    private String exp;

    public CreditCard(String number, String cvv, String date){
        this.number = number;
        this.cvv = cvv;
        this.exp = date;
    }

    public void insert(int cust_id) {
        DBConnection.insertTuple("insert into cc(ccid, id, cvv, exp) values('" + getNumber() + "', '" + cust_id +
                "', '" + getCvv() + "', " + "to_date('" + getExp() + "', 'yyyy/mm/dd'))");
    }

    public String getNumber() {
        return number;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExp() {
        return exp;
    }
}
