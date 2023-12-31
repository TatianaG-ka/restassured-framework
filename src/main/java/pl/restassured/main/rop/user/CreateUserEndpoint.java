package pl.restassured.main.rop.user;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pl.restassured.main.request.configuration.RequestConfigurationBuilder;
import pl.restassured.main.pojo.ApiResponse;
import pl.restassured.main.pojo.user.User;
import pl.restassured.main.rop.BaseEndpoint;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class CreateUserEndpoint extends BaseEndpoint<CreateUserEndpoint, ApiResponse> {
    private User user;

    @Override
    protected Type getModelType() {
        return ApiResponse.class;
    }

    @Step("Create user")
    @Override
    public CreateUserEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .body(user)
                .when().post("user");
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public CreateUserEndpoint setUser(User user) {
        this.user = user;
        return this;
    }

}
