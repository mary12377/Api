package tests.api;

import config.User;


import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;


import static helpers.AllureRestAssuredFilter.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static helpers.Specs.*;


public class KazanExpressTest  {

    @BeforeAll
    public static void setup() {
        RestAssured.filters(withCustomTemplates());}

    @Test
    @DisplayName("Добавить товар")
    void addProduct() {

        User user = new User();
        User response = given()
                .spec(request)
                .when()
                .get("/v2/product/1875667")
                .then()
                .spec(responseSpec200)
                .log().body()
                .extract().as(User.class);

        assertEquals(response.getId(), user.getId());
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

       assertNotEquals(response.getEmail(),"Мария" );
       assertNotEquals(response.getPassword(),"cityslicka1A" );

    }

    @Test
    @DisplayName("Ищем товар")
    void product() {

        String body = "{\"id\": \"250186\", \"title\": \"Велосипедки женские, шорты спортивные\" }";

        User response = given()
                .spec(request)
                .body(body)
                .when()
                .log().all()
                .get("/v2/product/250186")
                .then()
                .log().all()
                .spec(responseSpec200)
                .extract().as(User.class);

        assertNotEquals(response.getId(),"250186" );
    }
}
