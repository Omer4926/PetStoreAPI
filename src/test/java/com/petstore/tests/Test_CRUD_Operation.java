package com.petstore.tests;

import com.petstore.pojo.PetStoreUser;
import com.petstore.utilities.PetStoreTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class Test_CRUD_Operation extends PetStoreTestBase {

    @Test
    public void CRUD_Operation() {

        PetStoreUser petStoreUser = new PetStoreUser();

        petStoreUser.setUsername("Omer");
        petStoreUser.setFirstName("Omer");
        petStoreUser.setLastName("Guden");
        petStoreUser.setEmail("omerbakiguden@gmail.com");
        petStoreUser.setPassword("user1996");
        petStoreUser.setPhone("5543498284");
        petStoreUser.setUserStatus(48);

        RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(petStoreUser)
                .when().post("/user")
                .then().statusCode(200).extract().response().prettyPrint();

        RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("username", petStoreUser.getUsername())
                .when().get("/user/{username}")
                .then().statusCode(200).extract().response().prettyPrint();

        PetStoreUser updateUser = new PetStoreUser();

        updateUser.setUsername("Asu");
        updateUser.setFirstName("Asu");
        updateUser.setLastName("Simal");
        updateUser.setEmail("Asuguden@gmail.com");
        updateUser.setPassword("user1995");
        updateUser.setPhone("5553498285");
        updateUser.setUserStatus(49);

        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and().pathParam("username", petStoreUser.getUsername())
                .body(updateUser)
                .when().put("/user/{username}")
                .then().statusCode(200).extract().response().prettyPrint();

        RestAssured.given().accept(ContentType.JSON)
                .pathParam("username", updateUser.getUsername())
                .when().delete("/user/{username}")
                .then().statusCode(200).extract().response().prettyPrint();


    }

    @Test
    public void negativeScenario(){

        PetStoreUser petStoreUser = new PetStoreUser();

        petStoreUser.setUsername("Omer");
        petStoreUser.setFirstName("Omer");
        petStoreUser.setLastName("Guden");
        petStoreUser.setEmail("omerbakiguden@gmail.com");
        petStoreUser.setPassword("user1996");
        petStoreUser.setPhone("5543498284");
        petStoreUser.setUserStatus(48);

        RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(petStoreUser)
                .when().post("/userr")
                .then().statusCode(404).extract().response().prettyPrint();

        RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("username", petStoreUser.getLastName())
                .when().get("/user/{username}")
                .then().statusCode(404)
               // .then().body("code", Matchers.equalTo(404))
                .extract().response().prettyPrint();

        PetStoreUser updateUser = new PetStoreUser();

        updateUser.setUsername("Asu");
        updateUser.setFirstName("Asu");
        updateUser.setLastName("Simal");
        updateUser.setEmail("Asuguden@gmail.com");
        updateUser.setPassword("user1995");
        updateUser.setPhone("5553498285");
        updateUser.setUserStatus(49);

        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and().pathParam("username", petStoreUser.getLastName())
                .body(updateUser)
                .when().put("/user/{username}")
                .then().statusCode(404).extract().response().prettyPrint();

        RestAssured.given().accept(ContentType.JSON)
                .pathParam("username", updateUser.getLastName())
                .when().delete("/user/{username}")
                .then().statusCode(404).extract().response().prettyPrint();



    }

}
