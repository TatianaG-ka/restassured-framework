package pl.restassured.main.test.data.pet;

import pl.restassured.main.pojo.pet.Category;
import pl.restassured.main.pojo.pet.Pet;
import pl.restassured.main.pojo.pet.Tag;
import pl.restassured.main.test.data.TestDataGenerator;


import java.util.Collections;
import java.util.Random;


public class PetTestDataGenerator extends TestDataGenerator {

    public Pet generatePet() {
        PetsCategory petsCategory = randomPetCategory();
        PetTags petTags = randomPetTag();

        Category category = new Category();
        category.setId(petsCategory.getId());
        category.setName(petsCategory.getCategoryName());

        Tag tag = new Tag();
        tag.setId(petTags.getId());
        tag.setName(petTags.getTagName());

        Pet pet = new Pet();
        pet.setName(faker().gameOfThrones().character());
        pet.setId(faker().number().numberBetween(1, 9999));
        pet.setCategory(category);
        pet.setTags(Collections.singletonList(tag));
        pet.setPhotoUrls(Collections.singletonList(faker().internet().url()));

        PetStatus petStatus = randomPetStatus();
        pet.setStatus(petStatus.getStatus());
        return pet;
    }


    private PetTags randomPetTag() {
        int pick = new Random().nextInt(PetTags.values().length);
        return PetTags.values()[pick];
    }

    private PetsCategory randomPetCategory() {
        int pick = new Random().nextInt(PetsCategory.values().length);
        return PetsCategory.values()[pick];
    }

    private PetStatus randomPetStatus() {
        int pick = new Random().nextInt(PetStatus.values().length);
        return PetStatus.values()[pick];
    }

}