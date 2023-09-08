package pl.restassured.main.rop.pet;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pl.restassured.main.request.configuration.RequestConfigurationBuilder;
import pl.restassured.main.pojo.pet.Pet;
import pl.restassured.main.rop.BaseEndpoint;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class GetPetEndpoint extends BaseEndpoint<GetPetEndpoint, Pet> {

    private Integer petId;

    @Override
    protected Type getModelType() {
        return Pet.class;
    }

    @Step("Get Pet")
    @Override
    public GetPetEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .when().get("pet/{petId}", petId);
        return this;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public GetPetEndpoint setPetId(Integer petId) {
        this.petId = petId;
        return this;
    }

}