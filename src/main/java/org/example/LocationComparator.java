package org.example;
import java.util.Comparator;
public class LocationComparator implements Comparator<Package> {
    @Override
    public int compare(Package p1, Package p2) {
        if (!(p1.location.equals(p2.location))) {
            return p1.location.compareTo(p2.location);
        } else {
            return p1.date.compareTo(p2.date);
        }
    }
}