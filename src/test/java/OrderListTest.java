import client.ScooterServiceClient;
import client.ScooterServiceClientImple;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class OrderListTest {
    private ScooterServiceClient client;
    @Test
    @Description("Проверка - в тело ответа возвращается список заказов.")
    public void createOrders(){
        client = new ScooterServiceClientImple();
        ValidatableResponse response = client.orderList();
        response.assertThat().statusCode(200).and().body("orders", notNullValue());
    }

}
