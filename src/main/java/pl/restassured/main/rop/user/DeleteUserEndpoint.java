package pl.restassured.main.rop.user;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pl.restassured.main.request.configuration.RequestConfigurationBuilder;
import pl.restassured.main.pojo.ApiResponse;
import pl.restassured.main.rop.BaseEndpoint;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class DeleteUserEndpoint extends BaseEndpoint<DeleteUserEndpoint, ApiResponse> {

    private String username;

    @Override
    protected Type getModelType() {
        return ApiResponse.class;
    }

    @Step("Delete user")
    @Override
    public DeleteUserEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .when().delete("user/{username}", username);
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public DeleteUserEndpoint setUsername(String username) {
        this.username = username;
        return this;
    }
}
