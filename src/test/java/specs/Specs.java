package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

public class Specs {
    public static RequestSpecification request = with()

            .contentType(ContentType.JSON)
            .baseUri("https://api.kazanexpress.ru")
            .basePath("/api")
            .log().all();

    public static ResponseSpecification responseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification responseSpec405 = new ResponseSpecBuilder()
            .expectStatusCode(405)
            .build();
    public static ResponseSpecification responseSpec401 = new ResponseSpecBuilder()
            .expectStatusCode(401)
            .build();
}
