package com.petstore.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class PetStoreTestBase {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI ="https://petstore.swagger.io/v2";
    }



}
