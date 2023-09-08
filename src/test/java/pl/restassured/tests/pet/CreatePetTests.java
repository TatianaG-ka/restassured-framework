package pl.restassured.tests.pet;

import io.qameta.allure.Description;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import pl.restassured.main.pojo.pet.Pet;
import pl.restassured.main.rop.pet.CreatePetEndpoint;
import pl.restassured.main.test.data.pet.PetTestDataGenerator;
import pl.restassured.tests.testbases.PetTestBase;


public class CreatePetTests extends PetTestBase {

    private Pet actualPet;

    @Description("The goal of this test is to create pet and check if returned Pet object is the same")
    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {
        Pet pet = new PetTestDataGenerator().generatePet();

        actualPet = new CreatePetEndpoint().setPet(pet).sendRequest().assertRequestSuccess().getResponseModel();

        petIdToDelete = actualPet.getId();
        Assertions.assertThat(actualPet).describedAs("Send Pet was different than received by API")
                .usingRecursiveComparison().isEqualTo(pet);
    }

}


