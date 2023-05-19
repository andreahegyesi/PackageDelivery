package org.example;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Delivery extends Thread {
    public String location;
    public int distance;
    Date date;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public Delivery(String location, int distance, Date date) {
        this.location = location;
        this.distance = distance;
        this.date = date;
    }

    public void run() {
        System.out.println("[Delivering for <" + location + "> and date <" + df.format(date) + "> in <" + distance + "> seconds.]");
        try {
            this.sleep(distance * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("[Delivered to <" + location + "> on date <" + df.format(date) + ">]");
    }
}