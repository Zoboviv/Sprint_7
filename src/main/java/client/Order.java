package client;

import java.util.Arrays;

public class Order {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;

    public static Order create(String firstName, String lastName, String address, String metroStation,
                 String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        return new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
    }

    public Order(String firstName, String lastName, String address, String metroStation,
                 String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = new String[]{Arrays.toString(color)};
    }

}
