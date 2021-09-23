package APITestSuit;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UsersPostTests extends BaseTest {

    @Test
    public void checkPostResponseStatusWithCorrectBody() {
        JSONObject requestBody = new JSONObject();
        requestBody.put(id, 11);
        requestBody.put(userName, "User 11");
        requestBody.put(password, "Password11");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void checkPostResponseWithCorrectBody() {
        JSONObject requestBody = new JSONObject();
        requestBody.put(id, 11);
        requestBody.put(userName, "User 11");
        requestBody.put(password, "Password11");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .post()
                .then()
                .assertThat()
                .body(id, equalTo(11))
                .body(userName, equalTo("User 11"))
                .body(password, equalTo("Password11"));
    }

    @Test
    public void checkPostResponseForEntityThatAlreadyExists() {
        JSONObject requestBody = new JSONObject();
        requestBody.put(id, 2);
        requestBody.put(userName, "User 0");
        requestBody.put(password, "Password0");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .post()
                .then()
                .assertThat()
                .body(id, equalTo(2))
                .body(userName, equalTo("User 0"))
                .body(password, equalTo("Password0"));
    }

    @Test
    public void checkPostResponseWithEmptyFields() {
        JSONObject requestBody = new JSONObject();
        requestBody.put(id, 11);
        requestBody.put(userName, "");
        requestBody.put(password, "");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(id, equalTo(11))
                .body(userName, equalTo(""))
                .body(password, equalTo(""));
    }

    @Test
    public void checkPostErrorResponseWithEmptyIDAndFields() {
        JSONObject requestBody = new JSONObject();
        requestBody.put(id, 11);
        requestBody.put(userName, "");
        requestBody.put(password, "");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .post()
                .then()
                .assertThat()
                .body(id, equalTo(11))
                .body(userName, equalTo(""))
                .body(password, equalTo(""));
    }

    @Test
    public void checkPostResponseWithCorrectBodyAddedToDataBase() {
        JSONObject requestBody = new JSONObject();
        requestBody.put(id, 11);
        requestBody.put(userName, "User 11");
        requestBody.put(password, "Password11");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .post()
                .then()
                .assertThat()
                .body(id, equalTo(11))
                .body(userName, equalTo("User 11"))
                .body(password, equalTo("Password11"));

        given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .assertThat()
                .body("size()", is(usersSize + 1));
    }
}
