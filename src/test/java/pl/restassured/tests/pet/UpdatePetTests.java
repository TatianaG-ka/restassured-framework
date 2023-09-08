package pl.restassured.tests.pet;

import io.qameta.allure.Description;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.restassured.main.pojo.pet.Pet;
import pl.restassured.main.rop.pet.CreatePetEndpoint;
import pl.restassured.main.rop.pet.GetPetEndpoint;
import pl.restassured.main.rop.pet.UpdatePetEndpoint;
import pl.restassured.main.test.data.pet.PetTestDataGenerator;
import pl.restassured.tests.testbases.PetTestBase;

public class UpdatePetTests extends PetTestBase {
    private Pet petBeforeUpdate;

    @BeforeMethod
    public void beforeTest() {
        Pet pet = new PetTestDataGenerator().generatePet();
        petBeforeUpdate = new CreatePetEndpoint().setPet(pet).sendRequest().assertRequestSuccess().getResponseModel();
        petIdToDelete = petBeforeUpdate.getId();
    }

    @Description("The goal of this test is to update pet and check if it was updated")
    @Test
    public void givenPetWhenPetGetsUpdatedThenPetIsUpdatedTest() {
        Pet newPet = new PetTestDataGenerator().generatePet();
        newPet.setId(petBeforeUpdate.getId());

        Pet updatedPet = new UpdatePetEndpoint().setPet(newPet).sendRequest().assertRequestSuccess().getResponseModel();

        Assertions.assertThat(updatedPet).describedAs("Updated pet was the same as create pet")
                .usingRecursiveComparison().isNotEqualTo(petBeforeUpdate);
        Assertions.assertThat(updatedPet).describedAs("Updated pet was not the as same as update")
                .usingRecursiveComparison().isEqualTo(newPet);

        Pet petAfterUpdate = new GetPetEndpoint().setPetId(petBeforeUpdate.getId()).sendRequest()
                .assertRequestSuccess().getResponseModel();

        Assertions.assertThat(petAfterUpdate).describedAs("Updated pet was the same as create pet")
                .usingRecursiveComparison().isNotEqualTo(petBeforeUpdate);
        Assertions.assertThat(petAfterUpdate).describedAs("Updated pet was not the as same as update")
                .usingRecursiveComparison().isEqualTo(newPet);
    }

}


