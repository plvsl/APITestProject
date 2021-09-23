import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class APITestClass {

    @BeforeTest
    public static void setup() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/Users";
    }

    @Test
    public void checkGetStatusAndBodyIsNotEmpty() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("results", hasSize(greaterThan(0)))
                .log().all();
    }

    @Test
    public void checkGetFirstResult() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/{id}", "1")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("userName", equalTo("User 1"))
                .body("password", equalTo("Password1"));
    }

    @Test
    public void checkGetSecondResult() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/{id}", "2")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("userName", equalTo("User 2"))
                .body("password", equalTo("Password2"));
    }

    @Test
    public void checkGetResultWithIdNotExist() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/{id}", "11")
                .then()
                .assertThat()
                .statusCode(404)
                .and()
                .body("title", equalTo("Not Found"));
    }

    @Test
    public void checkGetErrorWhenCharacterID() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/{id}", "a")
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("errors.id[0]", equalTo("The value 'a' is not valid."));
    }

    @Test
    public void checkPostRequestWorkCorrectly() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 11);
        requestBody.put("userName", "User 11");
        requestBody.put("password", "Password11");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    public void checkPutRequestWorkCorrectly() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", 1);
        requestBody.put("userName", "Polina");
        requestBody.put("password", "Vasileva");

        // check response result

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", "1")
                .body(requestBody.toJSONString())
                .when()
                .put( "/{id}")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("userName", equalTo("Polina"))
                .body("password", equalTo("Vasileva"));

        // check database result

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", "1")
                .when()
                .get( "/{id}")
                .then()
                .assertThat()
                .body("userName", equalTo("Polina"))
                .body("password", equalTo("Vasileva"));
    }

    @Test
    public void checkDeleteRequestWorkCorrectly() {

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", "1")
                .when()
                .delete("/{id}")
                .then()
                .assertThat()
                .statusCode(200);
    }
}
