package Model;

/**
 * Created by MarkSchatzman on 4/26/17.
 */
public class DiscountGroup {
    private String name;
    private String code;
    private int discount;

    public DiscountGroup(String name, String discount_code, int discount){
        this.name = name;
        this.code = discount_code;
        this.discount = discount;
    }

    public void insert() {
        DBConnection.insertTuple("insert into discount_group(name, code, discount) values('" + getName() +
        "', '" + getDiscount_code() + "', '" + getDiscount() + "')");
    }

    public String getName() {
        return name;
    }

    public String getDiscount_code() {
        return code;
    }

    public int getDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscount_code(String discount_code) {
        this.code = discount_code;
    }
}
