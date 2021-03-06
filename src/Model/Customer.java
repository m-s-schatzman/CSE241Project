package Model;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by MarkSchatzman on 4/26/17.
 */
public class Customer {
    private String name;
    private String street;
    private String city;
    private String region;
    private String gid;

    public Customer(String name, String street, String city, String region){
        this.name = name;
        this.street = street;
        this.city = city;
        this.region = region;
        this.gid = "";
    }

    public void insert() {
        DBConnection.insertTuple("insert into customer(name, street, city, region, gid) values('" + getName() +
                "', '" + getStreet() + "', '" + getCity() + "', '" + getRegion() + "', '" +
                getGid() + "')");
    }

    public String getName() {
        return name;
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

    public String getGid() {
        return gid;
    }

    public static void showCustomers(){
        String cust_id;
        String name;
        String lid;
        ResultSet rs = DBConnection.getTuple("select customer.id, name, lid, exp " +
                "from customer left outer join license on customer.id = license.id order by customer.id");
        String date;
        System.out.printf("%-4s%-26s%-20s%-11s\n", "ID", "Name", "License Number", "License Exp");
        System.out.printf("%-4s%-26s%-20s%-11s\n", "---", "-------------------------", "-------------------", "----------");
        try {
            while (rs.next()) {
                cust_id = rs.getString("id");
                name = rs.getString("name");
                lid = rs.getString("lid");
                try {
                    date = rs.getString("exp").split(" ")[0];
                    System.out.printf("%-4s%-26s%-20s%-11s\n", cust_id, name, lid, date);
                }
                catch(java.lang.NullPointerException ex){
                    System.out.printf("%-4s%-26s\n", rs.getString("id") + ": ", rs.getString("name"));
                }
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }


    public void setGid(String gid) {
        this.gid = gid;
    }

    public static String getCustId(Customer c){
        String id = "";
        ResultSet rs = DBConnection.getTuple("select id from customer where name = '" + c.getName() + "' and street = '" +
        c.getStreet() + "' and city = '" + c.getCity() + "' and region = '" + c.getRegion() + "'");
        try {
            if (rs.next()) {
                id = rs.getString("id");
            }
        }
        catch(java.sql.SQLException ex){
            System.out.println("Customer not found!");
        }
        return id;
    }

    public static String getCustId(String choice){
        String id = "";
        ResultSet rs = DBConnection.getTuple("select id from customer where id = " + choice);
        try {
            if (rs.next()) {
                id = rs.getString("id");
            }
        }
        catch(java.sql.SQLException ex){
            System.out.println("Customer not found!");
        }
        return id;
    }

    public static String getCustName(String cust_id){
        String name = "";
        ResultSet rs = DBConnection.getTuple("select name from customer where id = " + cust_id);
        try {
            if (rs.next()) {
                name = rs.getString("name");
            }
        }
        catch(java.sql.SQLException ex){
            System.out.println("Customer not found!");
        }
        return name;
    }
}
