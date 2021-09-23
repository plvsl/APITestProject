package APITestSuit;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected String userName = "userName";
    protected String password = "password";
    protected String id = "id";
    protected String idURL = "/{id}";
    protected int usersSize = 10;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/Users";
    }
}
