package pl.restassured.main.rop.pet;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pl.restassured.main.request.configuration.RequestConfigurationBuilder;
import pl.restassured.main.pojo.ApiResponse;
import pl.restassured.main.rop.BaseEndpoint;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class UpdatePetNameStatusEndpoint extends BaseEndpoint<UpdatePetNameStatusEndpoint, ApiResponse> {
    private Integer petId;
    private String name;
    private String status;

    @Override
    protected Type getModelType() {
        return ApiResponse.class;
    }

    @Step("Update Pet name and status")
    @Override
    public UpdatePetNameStatusEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification().contentType("application/x-www-form-urlencoded"))
                .when()
                .formParam("name", name)
                .formParam("status", status)
                .post("pet/{petId}", petId);
        return this;
    }
    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public UpdatePetNameStatusEndpoint setPetId(Integer petId) {
        this.petId = petId;
        return this;
    }

    public UpdatePetNameStatusEndpoint setName(String name) {
        this.name = name;
        return this;
    }

    public UpdatePetNameStatusEndpoint setStatus(String status) {
        this.status = status;
        return this;
    }
}
