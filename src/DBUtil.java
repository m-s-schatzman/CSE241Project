import Model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by MarkSchatzman on 4/26/17.
 */
public class DBUtil {
    private static Scanner scan = null;

    public static void populate() {
        DBConnection.connect();
        addDiscountGroups();
        addCustomers();
        addLicenses();
        addCreditCards();
        addLocations();
        addVehicles();
        DBConnection.close();
    }

    public static void addDiscountGroups(){
        ArrayList<DiscountGroup> discountGroups = new ArrayList<>();
        discountGroups.add(new DiscountGroup("Business Guys", "FJ83J", 56));
        discountGroups.add(new DiscountGroup("Republicans", "JG73J", 109));
        discountGroups.add(new DiscountGroup("Coders", "DS999", 200));
        discountGroups.add(new DiscountGroup("We The People", "JJ489", 67));
        discountGroups.add(new DiscountGroup("Not Messing Around", "BN832", 54));
        for (DiscountGroup d:
             discountGroups) {
            d.insert();
        }
    }

    public static void addCustomers(){
        String name;
        String street;
        String city;
        String region;
        try {
            scan = new Scanner(new File("customers.csv"));
        }
        catch(FileNotFoundException ex) {
            ex.getMessage();
        }
        scan.useDelimiter(",");
        ArrayList<Customer> customers = new ArrayList<>();
        scan.nextLine();
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] arr = line.split(",");
            name = arr[0];
            street = arr[1];
            city = arr[2];
            region = arr[3];
            customers.add(new Customer(name, street, city, region));
        }
        for (Customer c:
             customers) {
            c.insert();
        }
    }

    private static void addLocations(){
        String street;
        String city;
        String region;

        try {
            scan = new Scanner(new File("locations.csv"));
        }
        catch(FileNotFoundException ex) {
            ex.getMessage();
        }
        scan.useDelimiter(",");
        ArrayList<Location> locations = new ArrayList<>();
        scan.nextLine();
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] arr = line.split(",");
            street = arr[0];
            city = arr[1];
            region = arr[2];
            locations.add(new Location(street, city, region));
        }
        for (Location l:
                locations) {
            l.insert();
        }

    }

    public static void addVehicles(){
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        List<String> makes = Arrays.asList("BMW", "Chrysler", "Honda", "Ferrari", "Mazda", "Jeep", "Volvo", "Isuzu",
                "Citrogen", "Amc", "Ford", "Toyota", "Chevrolet", "Pontiac");
        List<String> models = Arrays.asList("Axium", "Gremlin", "Pacer", "Edsel", "A6", "A8");
        List<String> types = Arrays.asList("Minicar", "SUV", "Minivan", "Compact", "Subcompact");
        /*
        vehicles.add(new Vehicle("Isuzu", "Axium", "2001", "SUV", 12000, 0));
        vehicles.add(new Vehicle("Citroen", "Deux Chevaux (2CV)", "1948", "minicar", 200000, 0));
        vehicles.add(new Vehicle("Citroen", "Deux Chevaux (2CV)", "1949", "minicar", 145940, 0));
        vehicles.add(new Vehicle("Citroen", "Deux Chevaux (2CV)", "1950", "minicar", 489345, 0));
        vehicles.add(new Vehicle("Citroen", "Deux Chevaux (2CV)", "1951", "minicar", 74392, 0));
        vehicles.add(new Vehicle("Trabant", "Trabant", "1990", "subcompact", 84335, 0));
        vehicles.add(new Vehicle("Trabant", "Trabant", "1970", "subcompact", 8935, 0));
        vehicles.add(new Vehicle("Plymouth", "Prowler", "1999", "sports car", 8349, 0));
        vehicles.add(new Vehicle("Citroen", "Deux Chevaux (2CV)", "1952", "minicar", 84359, 0));
        vehicles.add(new Vehicle("Citroen", "Deux Chevaux (2CV)", "1958", "minicar", 90435, 0));
        vehicles.add(new Vehicle("Citroen", "Deux Chevaux (2CV)", "1959", "minicar", 74385, 0));
        vehicles.add(new Vehicle("Citroen", "Deux Chevaux (2CV)", "1960", "minicar", 84935, 0));
        vehicles.add(new Vehicle("AMC", "Gremlin", "1971", "subcompact", 8342, 0));
        vehicles.add(new Vehicle("AMC", "Gremlin", "1972", "subcompact", 84353, 0));
        vehicles.add(new Vehicle("AMC", "Gremlin", "1973", "subcompact", 23454, 0));
        vehicles.add(new Vehicle("AMC", "Gremlin", "1974", "subcompact", 348952, 0));
        vehicles.add(new Vehicle("AMC", "Gremlin", "1975", "subcompact", 832432, 0));
        vehicles.add(new Vehicle("AMC", "Gremlin", "1976", "subcompact", 23428, 0));
        vehicles.add(new Vehicle("AMC", "Gremlin", "1977", "subcompact", 83423, 0));
        vehicles.add(new Vehicle("AMC", "Hornet", "1969", "compact", 34954, 0));
        vehicles.add(new Vehicle("AMC", "Hornet", "1970", "compact", 39435, 0));
        vehicles.add(new Vehicle("Ford", "Pinto", "1971", "compact", 8449, 0));
        vehicles.add(new Vehicle("Toyota", "Toyopet", "1970", "subcompact", 99343, 0));
        vehicles.add(new Vehicle("VAZ", "Lada", "1970", "compact", 33394, 0));
        vehicles.add(new Vehicle("Volkswagen", "Thing", "1970", "truck", 3934, 0));
        vehicles.add(new Vehicle("AMC", "Hornet", "1972", "compact", 54322, 0));
        vehicles.add(new Vehicle("Chevorlet", "Vega", "1971", "compact", 1111, 0));
        vehicles.add(new Vehicle("Chevorlet", "Vega", "1972", "compact", 11134, 0));
        vehicles.add(new Vehicle("Chevorlet", "Vega", "1973", "compact", 14911, 0));
        vehicles.add(new Vehicle("AMC", "Pacer", "1975", "compact", 11234, 0));
        vehicles.add(new Vehicle("AMC", "Pacer", "1976", "compact", 22934, 0));
        vehicles.add(new Vehicle("Ford", "Edsel", "1958", "full size", 99324, 0));
        vehicles.add(new Vehicle("Ford", "Edsel", "1959", "full size", 238432, 0));
        vehicles.add(new Vehicle("Ford", "Edsel", "1958", "full size", 54323, 0));
        vehicles.add(new Vehicle("Ford", "Edsel", "1960", "full size", 33234, 0));
        vehicles.add(new Vehicle("Chevorlet", "Vega", "1971", "compact", 111221, 0));
        vehicles.add(new Vehicle("Chevorlet", "Vega", "1974", "compact", 19431, 0));
        vehicles.add(new Vehicle("Chevorlet", "Vega", "1975", "compact", 19811, 0));
        vehicles.add(new Vehicle("Yugo", "Yugo", "2007", "subcompact", 9234, 0));
        vehicles.add(new Vehicle("Yugo", "Yugo", "2008", "subcompact", 92334, 0));
        vehicles.add(new Vehicle("Trabant", "Trabant", "1964", "subcompact", 8435, 0));
        vehicles.add(new Vehicle("Plymouth", "Prowler", "1999", "sports car", 8349, 0));
        vehicles.add(new Vehicle("Plymouth", "Prowler", "2000", "sports car", 83438, 0));
        vehicles.add(new Vehicle("Citroen", "Deux Chevaux (2CV)", "1953", "minicar", 34234, 0));
        vehicles.add(new Vehicle("Citroen", "Deux Chevaux (2CV)", "1954", "minicar", 54354, 0));
        vehicles.add(new Vehicle("Citroen", "Deux Chevaux (2CV)", "1955", "minicar", 124895, 0));
        vehicles.add(new Vehicle("Citroen", "Deux Chevaux (2CV)", "1956", "minicar", 89543, 0));
        vehicles.add(new Vehicle("Citroen", "Deux Chevaux (2CV)", "1957", "minicar", 54934, 0));
        vehicles.add(new Vehicle("Pontiac", "Aztek", "2001", "minivan", 83349, 0));
        vehicles.add(new Vehicle("Pontiac", "Aztek", "2004", "minivan", 83329, 0));*/
        Random rand = new Random();
        int randomNum = 0;
        for (Vehicle v:
             vehicles) {
            randomNum = rand.nextInt((Location.numLocations - 1) + 1) + 1;
            v.insert(randomNum);
        }
    }

    public static void addLicenses(){
        String id;
        String dobString;
        String expString;

        try {
            scan = new Scanner(new File("licenses.csv"));
        }
        catch(FileNotFoundException ex) {
            ex.getMessage();
        }
        scan.useDelimiter(",");
        ArrayList<License> licenses = new ArrayList<>();
        scan.nextLine();
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] arr = line.split(",");
            id = arr[0];
            dobString = arr[1];
            expString = arr[2];
            licenses.add(new License(id, dobString, expString));
        }
        int i = 1;
        for (License l:
                licenses) {
            l.insert(i++);
        }

    }

    public static void addCreditCards(){
        String number;
        String cvv;
        String exp;
        try {
            scan = new Scanner(new File("creditcards.csv"));
        }
        catch(FileNotFoundException ex) {
            ex.getMessage();
        }
        scan.useDelimiter(",");
        ArrayList<CreditCard> creditCards = new ArrayList<>();
        scan.nextLine();
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] arr = line.split(",");
            number = arr[0];
            cvv = arr[1];
            exp = arr[2];
            creditCards.add(new CreditCard(number, cvv, exp));
        }
        int i = 1;
        for (CreditCard cc:
                creditCards) {
            cc.insert(i++);
        }

    }

    public static void main(String[] args){
        DBUtil.populate();
        System.out.println("Database Populated.");
    }
}
