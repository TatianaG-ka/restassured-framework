package pl.restassured.tests.pet;

import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.restassured.main.rop.pet.DeletePetEndpoint;
import pl.restassured.tests.testbases.SuiteTestBase;

public class DeletePetTests extends SuiteTestBase {
    private int nonExistingPetId;

    @BeforeMethod
    public void beforeTest() {
        nonExistingPetId = new Faker().number().numberBetween(1000, 10000);
        new DeletePetEndpoint().setPetId(nonExistingPetId).sendRequest();
    }

    @Description("The goal of this test is to fail to delete non existing pet")
    @Test
    public void givenNonExistingPetWhenDeletingPetThenPetNotFoundTest() {
        new DeletePetEndpoint().setPetId(nonExistingPetId).sendRequest().assertStatusCode(HttpStatus.SC_NOT_FOUND);
    }

}

