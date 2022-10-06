package tests;


import io.restassured.response.Response;
import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static specs.Specs.*;


public class KazanExpressTest extends TestBase {

    @Test
    @DisplayName("Добавить товар")
    void addProduct() {

        Response response = given()
                .spec(request)
                .when()
                .get("/v2/product/1875667")
                .then()
                .spec(responseSpec200)
                .log().body()
                .extract().response();
    }

    @Test
    @DisplayName("Неудачная авторизация")
    void unsuccessfulAuthorization() {

        User user = new User();
        user.setEmail("sydney@fife");
        user.setPassword("pistol");

        User response = given()
                .spec(request)
                .body(user)
                .when()
                .post("/oauth/token")
                .then()
                .spec(responseSpec401)
                .log().body()
                .extract().as(User.class);

        assertEquals(response.getError(), "");
    }

   @Test
    @DisplayName("Успешная авторизация")
    void successfulAuthorization() {

        User user = new User();
        user.setEmail("Мария");
        user.setPassword("cityslicka1A");

       User response = given()
                .spec(request)
                .body(user)
                .when()
                .post("/oauth/token")
                .then()
                .spec(responseSpec401)
                .log().body()
                .extract().as(User.class);

       assertNotEquals(response.getEmail(),user.getEmail() );
       assertNotEquals(response.getPassword(),user.getPassword() );

    }

}
