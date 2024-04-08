import client.Order;
import client.ScooterServiceClient;
import client.ScooterServiceClientImple;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class OrderTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;
    private ScooterServiceClient client;


    public OrderTest(String firstName, String lastName, String address, String metroStation,
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

    @Parameterized.Parameters
    public static Object[][] getOrder() {
        return new Object[][] {
                { "Тест1", "Тест2", "Ленина, 142","ВДНХ" , "+7 800 355 35 35", 5 , "2024-04-01", "Комментарий",
                        new String[]{"BLACK"}},
                { "Тест1", "Тест2", "Ленина, 142","ВДНХ" , "+7 800 355 35 35", 5 , "2024-04-01", "Комментарий",
                        new String[]{"GREY"}},
                { "Тест1", "Тест2", "Ленина, 142","ВДНХ" , "+7 800 355 35 35", 5 , "2024-04-01", "Комментарий",
                        new String[]{"GREY","BLACK"}},
                { "Тест1", "Тест2", "Ленина, 142","ВДНХ" , "+7 800 355 35 35", 5 , "2024-04-01", "Комментарий",
                        new String[]{""}}
        };
    }

    @Test
    public void createOrders(){
        client = new ScooterServiceClientImple();
        Order order = Order.create(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ValidatableResponse response = client.createOrder(order);
        response.assertThat().statusCode(201).and().body("track", equalTo(response.extract().jsonPath().getInt("track")));
    }

}
