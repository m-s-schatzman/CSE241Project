package Model;

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

    public void setGid(String gid) {
        this.gid = gid;
    }
}
