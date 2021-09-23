package APITestSuit;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UsersPutTests extends BaseTest {

    @Test
    public void checkPutResponseWithCorrectBody() {
        JSONObject requestBody = new JSONObject();
        requestBody.put(id, 1);
        requestBody.put(userName, "Polina");
        requestBody.put(password, "PolinaPassword");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .put(idURL, "1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(id, equalTo(1))
                .body(userName, equalTo("Polina"))
                .body(password, equalTo("PolinaPassword"));
    }

    @Test
    public void checkPutResponseWithEmptyIDAndFields() {
        JSONObject requestBody = new JSONObject();
        requestBody.put(id, "");
        requestBody.put(userName, "");
        requestBody.put(password, "");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .put(idURL, "1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void checkPutResponseWithEmptyFields() {
        JSONObject requestBody = new JSONObject();
        requestBody.put(id, 3);
        requestBody.put(userName, "");
        requestBody.put(password, "");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .put(idURL, "3")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(id, equalTo(3))
                .body(userName, equalTo(""))
                .body(password, equalTo(""));
    }

    @Test
    public void checkPutResponseWithCorrectBodyChangedInDataBase() {
        JSONObject requestBody = new JSONObject();
        requestBody.put(id, 1);
        requestBody.put(userName, "Polina");
        requestBody.put(password, "PolinaPassword");

        // check response result
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .put(idURL, "1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(id, equalTo(1))
                .body(userName, equalTo("Polina"))
                .body(password, equalTo("PolinaPassword"));

        // check database result
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get(idURL, "1")
                .then()
                .assertThat()
                .body(id, equalTo(1))
                .body(userName, equalTo("Polina"))
                .body(password, equalTo("PolinaPassword"));
    }

}