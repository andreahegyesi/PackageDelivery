package org.example;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class DeliveryAppTest extends TestCase {
    public void testGetTotalValue() {
        DeliveryApp testApp = new DeliveryApp();
        List<Package> packageTestList = new ArrayList<>();
        Package package1 = new Package("Oradea", 158, 500, new Date(2019 - 05 - 19));
        Package package2 = new Package("Baciu", 10, 100, new Date(2023 - 05 - 18));
        Package package3 = new Package("Baciu", 10, 200, new Date(2023 - 05 - 18));
        packageTestList.add(package1);
        packageTestList.add(package2);
        packageTestList.add(package3);
        assertEquals(800, testApp.getTotalValue(packageTestList));
    }

    public void testGetTotalRevenue() {
        DeliveryApp testApp = new DeliveryApp();
        List<Package> packageTestList = new ArrayList<>();
        Package package1 = new Package("Oradea", 158, 500, new Date(2019 - 05 - 19));
        Package package2 = new Package("Baciu", 10, 100, new Date(2023 - 05 - 18));
        Package package3 = new Package("Baciu", 10, 200, new Date(2023 - 05 - 18));
        packageTestList.add(package1);
        packageTestList.add(package2);
        packageTestList.add(package3);
        assertEquals(168, testApp.getTotalRevenue(packageTestList));
    }
}