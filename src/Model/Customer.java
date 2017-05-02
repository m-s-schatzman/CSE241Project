package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        ArrayList<Customer> customers = new ArrayList<>();
        ResultSet rs = DBConnection.getTuple("select customer.id, name, lid, exp " +
                "from customer left outer join license on customer.id = license.id");
        String date;
        try {
            while (rs.next()) {
                try {
                    date = rs.getString("exp").split(" ")[0];
                }
                catch(java.lang.NullPointerException ex){
                    System.out.printf("%-3s%-25s\n", rs.getString("id") + ": ", rs.getString("name"));
                    continue;
                }
                System.out.printf("%-3s%-25s%-20s%-8s\n", rs.getString("id") + ": ", rs.getString("name"),
                        rs.getString("lid"), date);
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
}
