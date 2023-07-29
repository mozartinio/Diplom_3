package user;

import constans.ApiEndpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserSteps {
    @Step("Создание пользователя")
    public static Response createUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .post(ApiEndpoints.API_REGISTER);
    }

    @Step("Авторизация пользователя")
    public static Response authUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .post(ApiEndpoints.API_LOGIN);
    }

    @Step("Удаление пользователя")
    public static Response deleteUser(String authToken) {
        String URIaddress = ApiEndpoints.API_USER;
        return given()
                .header("Authorization", authToken)
                .delete(URIaddress);
    }
}
