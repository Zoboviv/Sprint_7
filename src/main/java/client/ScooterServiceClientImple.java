package client;

import io.restassured.response.ValidatableResponse;

import java.net.URI;

import static io.restassured.RestAssured.given;

public class ScooterServiceClientImple implements ScooterServiceClient {
    private static final String BASE_URI = "https://qa-scooter.praktikum-services.ru/";
    private static final String CREATE_USER_ENDPOINT = "/api/v1/courier";
    private static final String CREATE_LOGIN_ENDPOINT = "/api/v1/courier/login";
    private static final String CREATE_ORDER_ENDPOINT = "/api/v1/orders";


    @Override
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .log()
                .all()
                .header("Content-Type", "application/json")
                .baseUri(BASE_URI)
                .body(courier)
                .post(CREATE_USER_ENDPOINT)
                .then()
                .log()
                .all();

    }

    @Override
    public ValidatableResponse login(Credentials credentials) {
        return given()
                .log()
                .all()
                .header("Content-Type", "application/json")
                .baseUri(BASE_URI)
                .body(credentials)
                .post(CREATE_LOGIN_ENDPOINT)
                .then()
                .log()
                .all();
    }

    @Override
    public ValidatableResponse deleteCourierById(String id) {
        return given()
                .log()
                .all()
                .header("Content-Type", "application/json")
                .baseUri(BASE_URI)
                .delete(CREATE_USER_ENDPOINT + "/"+ id)
                .then()
                .log()
                .all();
    }

    @Override
    public ValidatableResponse createOrder(Order order) {
        return given()
                .log()
                .all()
                .header("Content-Type", "application/json")
                .baseUri(BASE_URI)
                .post(CREATE_ORDER_ENDPOINT)
                .then()
                .log()
                .all();
    }

    @Override
    public ValidatableResponse orderList() {
        return given()
                .log()
                .all()
                .header("Content-Type", "application/json")
                .baseUri(BASE_URI)
                .get(CREATE_ORDER_ENDPOINT)
                .then()
                .log()
                .all();
    }

}
