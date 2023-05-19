package org.example;
import java.util.Date;
import java.util.Objects;
public class Package implements Cloneable {
    public String location;
    public int distance;
    public int value;
    Date date;

    public Package(String location, int distance, int value, Date date) {
        this.location = location;
        this.distance = distance;
        this.value = value;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Package{" +
                "location='" + location + '\'' +
                ", distance=" + distance +
                ", value=" + value +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return distance == aPackage.distance && value == aPackage.value && Objects.equals(location, aPackage.location) && Objects.equals(date, aPackage.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, distance, value, date);
    }
}
