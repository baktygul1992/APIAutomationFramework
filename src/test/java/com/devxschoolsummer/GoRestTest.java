package com.devxschoolsummer;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GoRestTest {
    private  static Response apiResponse;
    @Test
    public void goRestTest_1(){
        Response response= when().request("get","https://gorest.co.in/public-api/users");
        JsonPath jsonPath=response.body().jsonPath();
        String name=jsonPath.getString("data[0].name");
        System.out.println(name);
    }
    @Test
    public void createUser_1(){
        //send a request to create a user
        //save a  response in class var
        // val response
        Response createdUserResponse=given()
                .header("Authorization","Bearer ec1e26147c3fb45452cd640baff2a1ea4cf8b761d85277eff5b9d2dabb7af927")
                .contentType(ContentType.JSON)

                .body("{\n" +
                        "    \"name\": \"Baktygul Abytalievna\",\n" +
                        "            \"email\": \"ab1@gmail.com\",\n" +
                        "            \"gender\": \"Female\",\n" +
                        "            \"status\": \"Active\"\n" +
                        "            }")
                .when().request("post", "https://gorest.co.in/public-api/users");
        createdUserResponse.prettyPrint();
        Assert.assertEquals(200,createdUserResponse.getStatusCode());
        String email=createdUserResponse.body().jsonPath().getString("data.email");
        Assert.assertEquals("ab1@gmail.com",email);
        // "id": 1447
        apiResponse=createdUserResponse;



    }

    public static  void getUserDetails_1(){

        //send req to get user details using ID of newly created person
        // validate response
        //String apiResponse=

    }
    @Test
    public void updateUser_1(){

    }
    @Test
    public void deleteUser_1(){

    }
}
