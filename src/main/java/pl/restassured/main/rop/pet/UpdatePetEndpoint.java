package pl.restassured.main.rop.pet;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pl.restassured.main.request.configuration.RequestConfigurationBuilder;
import pl.restassured.main.pojo.pet.Pet;
import pl.restassured.main.rop.BaseEndpoint;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class UpdatePetEndpoint extends BaseEndpoint<UpdatePetEndpoint, Pet> {
    private Pet pet;

    @Override
    protected Type getModelType() {
        return Pet.class;
    }

    @Step("Update Pet")
    @Override
    public UpdatePetEndpoint sendRequest() {
        response = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification()).body(pet)
                .when().put("pet");
        return this;
    }
    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    public UpdatePetEndpoint setPet(Pet pet) {
        this.pet = pet;
        return this;
    }
}
