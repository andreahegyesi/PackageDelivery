package org.example;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Small logistics application that simulates package delivery.
 */
public class DeliveryApp {
    static List<Package> packages = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        DeliveryApp app = new DeliveryApp();
        Scanner scanner = null;
        try {
            scanner = new Scanner(
                    new File("input.csv"));
            while (scanner.hasNextLine()) {
                List<String> line = parseLine(scanner.nextLine());
                app.packages.add(app.createPackage(line));
            }
        } catch (IOException e) {
            System.out.println("File not found");
            System.exit(0);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        Collections.sort(packages, new LocationComparator());
        Set<Package> groupedPackages = app.listToSet(packages);
        for (Package p : groupedPackages) {
            Thread t = new Delivery(p.location, p.distance, p.date);
            t.start();
            t.join();
        }
        System.out.println("Total value of all delivered packages: " + app.getTotalValue(packages) + " LEI");
        System.out.println("Total value of the revenue computed for all groups delivered: " + app.getTotalRevenue(packages) + " LEI");
        System.out.println("Goodbye!");
    }

    public static List<String> parseLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public Package createPackage(List<String> stringList) {
        String location = stringList.get(0);
        int distance = Integer.parseInt(stringList.get(1));
        int value = Integer.parseInt(stringList.get(2));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(stringList.get(3));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new Package(location, distance, value, date);
    }

    /**
     * Grouping the packages based on the target location and delivery date
     *
     * @param packageList
     * @return
     */
    public Set<Package> listToSet(List<Package> packageList) {
        TreeSet<Package> packageSet = new TreeSet<>(new LocationComparator());
        for (Package p : packageList) {
            if (packageSet.contains(p)) {
                packageSet.last().value += p.value;
            } else {
                packageSet.add(new Package(p.location, p.distance, p.value, p.date));
            }
        }
        return packageSet;
    }

    public int getTotalValue(List<Package> packageList) {
        int totalValue = 0;
        for (Package p : packageList) {
            totalValue += p.value;
        }
        return totalValue;
    }

    public int getTotalRevenue(List<Package> packageList) {
        int totalRevenue = 0;
        for (Package p : listToSet(packageList)) {
            totalRevenue += p.distance;
        }
        return totalRevenue;
    }
}
