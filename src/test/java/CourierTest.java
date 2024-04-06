import client.Courier;
import client.Credentials;
import client.ScooterServiceClient;
import client.ScooterServiceClientImple;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CourierTest {
    private ScooterServiceClient client;
    private String id;

    @After
    public void cleanUp(){
        ValidatableResponse deleteResponse = client.deleteCourierById(id);
        deleteResponse.assertThat().statusCode(200).and().body("ok",equalTo(true));
    }

    @Test
    public void createSuccesAndDelete(){
        client = new ScooterServiceClientImple();

        Courier courier = Courier.create("TestZobov","Test123", "testName");
        ValidatableResponse response = client.createCourier(courier);

        ValidatableResponse loginResponse = client.login(Credentials.fromCourier(courier));
        id = loginResponse.extract().jsonPath().getString("id");

        response.assertThat().statusCode(201).and().body("ok",equalTo(true));
    }

}
