package APITestSuit;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UsersGetTests extends BaseTest {

    @Test
    public void checkGetStatusAndBodyHasCorrectSize() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("size()", is(usersSize));
    }

    @Test
    public void checkGetFirstResult() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(idURL, "1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(id, equalTo(1))
                .body(userName, equalTo("User 1"))
                .body(password, equalTo("Password1"));
    }

    @Test
    public void checkGetNinthResult() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(idURL, "9")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body(id, equalTo(9))
                .body(userName, equalTo("User 9"))
                .body(password, equalTo("Password9"));
    }

    @Test
    public void checkGetResultWithNonExistentId() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(idURL, "11")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .and()
                .body("title", equalTo("Not Found"));
    }

    @Test
    public void checkGetErrorWhenCharacterID() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(idURL, "a")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .body("errors.id[0]", equalTo("The value 'a' is not valid."));
    }
}
