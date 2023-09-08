package pl.restassured.main.rop.pet;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pl.restassured.main.request.configuration.RequestConfigurationBuilder;
import pl.restassured.main.pojo.ApiResponse;
import pl.restassured.main.rop.BaseEndpoint;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class DeletePetEndpoint extends BaseEndpoint<DeletePetEndpoint, ApiResponse> {
    private Integer petId;

    @Override
    protected Type getModelType() {
        return ApiResponse.class;
    }

    @Step("Delete Pet")
    @Override
    public DeletePetEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .when().delete("pet/{petId}", petId);
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public DeletePetEndpoint setPetId(Integer petId) {
        this.petId = petId;
        return this;
    }
}
