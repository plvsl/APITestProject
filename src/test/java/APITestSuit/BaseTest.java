package APITestSuit;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected static final String userName = "userName";
    protected static final String password = "password";
    protected static final String id = "id";
    protected static final String idURL = "/{id}";
    protected int usersSize = 10;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1/Users";
    }
}
