package pl.restassured.tests.pet;

import io.qameta.allure.Description;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.restassured.main.pojo.pet.Pet;
import pl.restassured.main.rop.pet.CreatePetEndpoint;
import pl.restassured.main.rop.pet.GetPetEndpoint;
import pl.restassured.main.rop.pet.UpdatePetNameStatusEndpoint;
import pl.restassured.main.test.data.pet.PetStatus;
import pl.restassured.main.test.data.pet.PetTestDataGenerator;
import pl.restassured.tests.testbases.PetTestBase;

public class UpdatePetStatusTests extends PetTestBase {
    private Pet petBeforeUpdate;

    @BeforeMethod
    public void beforeTest() {
        Pet pet = new PetTestDataGenerator().generatePet();

        petBeforeUpdate = new CreatePetEndpoint().setPet(pet).sendRequest().assertRequestSuccess().getResponseModel();
        petIdToDelete = petBeforeUpdate.getId();
    }

    @Description("The goal of this test is to update pet name or/and status")
    @Test(dataProvider = "petStatusAndName")
    public void givenPetWhenPetGetsUpdatedNameOrStatusThenPetIsUpdatedTest(PetStatus petStatus) {
        new UpdatePetNameStatusEndpoint().setPetId(petBeforeUpdate.getId()).setStatus(petStatus.getStatus()).setName(petBeforeUpdate.getName()).sendRequest().assertRequestSuccess();

        Pet petAfterUpdate = new GetPetEndpoint().setPetId(petBeforeUpdate.getId()).sendRequest().assertRequestSuccess().getResponseModel();

        Assertions.assertThat(petAfterUpdate.getStatus()).describedAs("Pet Status").isEqualTo(petStatus.getStatus());
    }

    @DataProvider
    public Object[][] petStatusAndName() {
        return new Object[][]
                {
                        {PetStatus.AVAILABLE},
                        {PetStatus.PENDING},
                        {PetStatus.SOLD}
                };

    }

}