import client.Courier;
import client.Credentials;
import client.ScooterServiceClient;
import client.ScooterServiceClientImple;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CourierTest {
    private ScooterServiceClient client;
    private String id;

    @After
    @Step("Удаление курьера")
    public void deleteCourier(){
        if (id != null){
            ValidatableResponse deleteResponse = client.deleteCourierById(id);
            deleteResponse.assertThat().statusCode(200).and().body("ok",equalTo(true));
        }
    }

    @Test
    @Description ("Проверка - нельзя создать двух одинаковых курьеров")
    public void createTwoCouriers(){
        createCourier();
        client = new ScooterServiceClientImple();
        Courier courier = Courier.create("TestZobov","Test123", "testName");
        ValidatableResponse response = client.createCourier(courier);
        response.assertThat().statusCode(409).and().onFailMessage("Этот логин уже используется. Попробуйте другой.");
    }

    @Test
    @Description ("Проверка - нельзя создать курьера без логина")
    public void createWithoutLogin(){
        client = new ScooterServiceClientImple();
        Courier courier = Courier.create("","Test123", "testName");
        getIdCourier(courier);
        ValidatableResponse response = client.createCourier(courier);
        response.assertThat().statusCode(400).and().onFailMessage("Недостаточно данных для создания учетной записи");
    }

    @Test
    @Description ("Проверка - нельзя создать курьера без пароля")
    public void createWithoutPassword(){
        client = new ScooterServiceClientImple();
        Courier courier = Courier.create("TestZobov","", "testName");
        getIdCourier(courier);
        ValidatableResponse response = client.createCourier(courier);
        response.assertThat().statusCode(400).and().onFailMessage("Недостаточно данных для создания учетной записи");
    }

    @Test
    @Description ("Проверка - нельзя выполнить авторизацию без логина")
    public void performAuthorizationWithoutLogin(){
        createCourier();
        client = new ScooterServiceClientImple();
        Credentials credentials = Credentials.authorization("","Test123");
        ValidatableResponse response = client.login(credentials);
        response.assertThat().statusCode(400).and().onFailMessage("Недостаточно данных для входа");
    }

    @Test
    @Description ("Проверка - нельзя выполнить авторизацию без пароля")
    public void performAuthorizationWithoutPassword(){
        createCourier();
        client = new ScooterServiceClientImple();
        Credentials credentials = Credentials.authorization("TestZobov","");
        ValidatableResponse response = client.login(credentials);
        response.assertThat().statusCode(400).and().onFailMessage("Недостаточно данных для входа");
    }

    @Test
    @Description ("Проверка - нельзя выполнить авторизацию c несуществующим логином-паролем")
    public void performAuthorizationWithNonExistent(){
        client = new ScooterServiceClientImple();
        Credentials credentials = Credentials.authorization("TestZobov","Test123");
        ValidatableResponse response = client.login(credentials);
        response.assertThat().statusCode(404).and().onFailMessage("Учетная запись не найдена");
    }


    @Step ("Cоздание курьера")
    public void createCourier(){
        client = new ScooterServiceClientImple();
        Courier courier = Courier.create("TestZobov","Test123", "testName");
        ValidatableResponse response = client.createCourier(courier);
        getIdCourier(courier);
        response.assertThat().statusCode(201).and().body("ok",equalTo(true));
    }

    @Step("Получаем id курьера")
    public void getIdCourier(Courier courier){
        ValidatableResponse loginResponse = client.login(Credentials.fromCourier(courier));
        id = loginResponse.extract().jsonPath().getString("id");
    }

}
