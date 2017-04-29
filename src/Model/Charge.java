package Model;

/**
 * Created by MarkSchatzman on 4/28/17.
 */
public abstract class Charge {
    private String amount;
    private String date;

    public Charge(String amount, String date){
        this.amount = amount;
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}
