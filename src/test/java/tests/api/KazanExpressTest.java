package tests.api;

import config.User;


import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;
import static helpers.AllureRestAssuredFilter.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static helpers.Specs.*;


public class KazanExpressTest  {


    @Test
    @DisplayName("Добавить товар")
    void addProduct() {

        User user = new User();
        User response = given()
                .spec(request)
                .filter(withCustomTemplates())
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
                .filter(withCustomTemplates())
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
               .filter(withCustomTemplates())
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
    void productIdTest() {

        String body = "{\"id\": \"250186\", \"title\": \"Велосипедки женские, шорты спортивные\" }";

        User response = given()
                .spec(request)
                .filter(withCustomTemplates())
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

    @Test
    void requestProduct() {

        given()
                .spec(request)
                .filter(withCustomTemplates())
                .when()
                .log().all()// Раскроет всё тело запроса
                .get("/v2/product/250186")
                .then()
                .log().all()
                .spec(responseSpec200)
                .body("payload", notNullValue());
    }
}
