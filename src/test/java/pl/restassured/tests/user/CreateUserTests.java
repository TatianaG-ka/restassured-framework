package pl.restassured.tests.user;

import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pl.restassured.main.pojo.ApiResponse;
import pl.restassured.main.pojo.user.User;
import pl.restassured.main.rop.user.CreateUserEndpoint;
import pl.restassured.main.rop.user.DeleteUserEndpoint;
import pl.restassured.main.test.data.UserTestDataGenerator;
import pl.restassured.tests.testbases.SuiteTestBase;

public class CreateUserTests extends SuiteTestBase {
    private User user;

    @Description("The goal of this test is to create new user")
    @Test
    public void givenUserWhenPostUserThenUserIsCreatedTest() {
        UserTestDataGenerator userTestDataGenerator = new UserTestDataGenerator();
        user = userTestDataGenerator.generateUser();

        ApiResponse apiResponse = new CreateUserEndpoint().setUser(user).sendRequest().assertRequestSuccess().getResponseModel();

        ApiResponse expectedApiResponse = new ApiResponse();
        expectedApiResponse.setCode(HttpStatus.SC_OK);
        expectedApiResponse.setType("unknown");
        expectedApiResponse.setMessage(user.getId().toString());

        Assertions.assertThat(apiResponse).describedAs("Created User was not created by API")
                .usingRecursiveComparison().isEqualTo(expectedApiResponse);
    }

    @AfterMethod
    public void cleanUpAfterTest() {
        ApiResponse apiResponse = new DeleteUserEndpoint().setUsername(user.getUsername()).sendRequest()
                .assertRequestSuccess().getResponseModel();

        ApiResponse expectedApiResponse = new ApiResponse();
        expectedApiResponse.setCode(HttpStatus.SC_OK);
        expectedApiResponse.setType("unknown");
        expectedApiResponse.setMessage(user.getUsername());

        Assertions.assertThat(apiResponse).describedAs("User was not deleted")
                .usingRecursiveComparison().isEqualTo(expectedApiResponse);
    }


}
