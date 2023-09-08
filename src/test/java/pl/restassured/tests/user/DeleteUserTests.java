package pl.restassured.tests.user;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.restassured.main.rop.user.DeleteUserEndpoint;
import pl.restassured.tests.testbases.SuiteTestBase;

public class DeleteUserTests extends SuiteTestBase {
    private String nonExistingUsername;

    @BeforeMethod
    public void beforeTest() {
        nonExistingUsername = new Faker().name().username();
        new DeleteUserEndpoint().setUsername(nonExistingUsername).sendRequest();
    }

    @Description("The goal of this test is to fail to delete non-existing user")
    @Test
    public void givenNonExistingUserWhenDeletingUserThenUserNotFoundTest() {

        new DeleteUserEndpoint().setUsername(nonExistingUsername).sendRequest()
                .assertStatusCode(HttpStatus.SC_NOT_FOUND);
    }
}
