package com.devxschoolsummer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    //@Before
   // public void setUp(){
        //baseURI="https://jsonplaceholder.typicode.com";
    //}


    @Test
    public void getUserDetailsById1(){
       // int id=
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("https://jsonplaceholder.typicode.com/users/4")
                .then()
                //.extract()
                //.path("id")
                .statusCode(200)
                .body("address.street", Matchers.equalTo("Hoeger Mall") )
                .body("company.name",Matchers.equalTo("Robel-Corkery"));
       // Assert.assertTrue(4,id);
    }
    @Test
    public void getUserDetailsById2(){
        // given() section is optional and it is used to build request
        when()
                .get("https://jsonplaceholder.typicode.com/users/4")
                .then()
                .statusCode(200);

    }
    @Test
    public void getUserDetailsById3(){
        Response response=when().get("https://jsonplaceholder.typicode.com/users/4");
        Assert.assertEquals(200,response.getStatusCode());
        int id=response.body().jsonPath().getInt("id");
        Assert.assertEquals(4,id);
    }
    @Test
    public void getUserDetailsById4(){
        Response response= when().request("get","https://jsonplaceholder.typicode.com/users/4");
        Assert.assertEquals(200,response.getStatusCode());
        int id=response.body().jsonPath().getInt("id");
        Assert.assertEquals(4,id);

    }
    @Test
    public void practice1(){
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/users/4")
                .then()
                .body("address.suite",Matchers.equalTo("Apt. 692"))
                .body("address.city",Matchers.equalTo("South Elvis"))
                .statusCode(200);
    }
    @Test
    public void practice2(){
        Response response=when().get("https://jsonplaceholder.typicode.com/users/4");
        String lng=response.body().jsonPath().getString("address.geo.lng");
        Assert.assertEquals("-164.2990",lng);
        String catchPhrase=response.body().jsonPath().getString("company.catchPhrase");
        Assert.assertEquals("Multi-tiered zero tolerance productivity",catchPhrase);
    }

    @Test
    public void practice3(){
        Response response=when().request("get","https://jsonplaceholder.typicode.com/users");
        int id=response.body().jsonPath().getInt("[1].id");
        System.out.println(id);
        Assert.assertEquals(2,id);
    }
    @Test
    public void createUser1(){
        Response response=given()
                .contentType(ContentType.JSON)
        .body("{\n" +
                "    \n" +
                "    \"name\": \"Baktygul\",\n" +
                "    \"username\": \"baktygulib\",\n" +
                "    \"email\": \"bak_@gmail.com\"\n" +
                "\n" +
                "}\n")
        .when().request("post","https://jsonplaceholder.typicode.com/users");
        Assert.assertEquals(201, response.getStatusCode());
        String name=response.body().jsonPath().getString("name");
        Assert.assertEquals("Baktygul",name);

    }
@Test
    public void homeWork(){
        Response response=when().request("get","https://jsonplaceholder.typicode.com/users");
        int id=response.body().jsonPath().getInt("[6].id");
        Assert.assertEquals(7,id);
    Response response1=when().request("get","https://jsonplaceholder.typicode.com/users/1");
        String username=response1.body().jsonPath().getString("username");
        Assert.assertEquals("Bret",username);
}
@Test
public void homeWork1(){
        Response response=given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "     \"name\": \"Aman\",\n" +
                        "    \"username\": \"aman123\",\n" +
                        "    \"email\": \"aman@gmail.com\"\n" +
                        "}")
        .when().request("post","https://jsonplaceholder.typicode.com/users");
        Assert.assertEquals(201,response.getStatusCode());
        String name=response.body().jsonPath().getString("name");
        Assert.assertEquals("Aman",name);
        String email=response.body().jsonPath().getString("email");
        assertEquals("aman@gmail.com",email);

}
@Test
    public void hwGetPosts(){
        Response response=when().request("get","https://jsonplaceholder.typicode.com/posts/2");
        int id=response.body().jsonPath().getInt("id");
        Assert.assertEquals(2,id);
        String title=response.body().jsonPath().getString("title");
        Assert.assertEquals("qui est esse",title);

}
    @Test
    public void hwCreatePosts(){
        Response response=given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"id\": 12,\n" +
                        "    \"title\": \"My name is Bakulya\",\n" +
                        "    \"body\": \"My first text in json format\"\n" +
                        "}")
                .when().request("post","https://jsonplaceholder.typicode.com/posts");
        Assert.assertEquals(201, response.getStatusCode());
        String title=response.body().jsonPath().getString("title");
        Assert.assertEquals("My name is Bakulya", title);


    }
    @Test
    public void hwUpdatePosts(){
        Response response=given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\n" +
                        "   \n" +
                        "        \"title\": \"Baktygul\",\n" +
                        "        \"body\": \"Baktygul Ibraimova \"\n" +
                        "}")
                .when().request("put","https://jsonplaceholder.typicode.com/posts/3");
        System.out.println(response.asString());
            //response.then().body("id",Matchers.is(3));
            //response.then().body("title",Matchers.is("new1 ea molestias quasi exercitationem repellat qui ipsa sit aut updated"));
        String title=response.body().jsonPath().getString("title");
        Assert.assertEquals("Baktygul",title);


    }
    @Test
    public void hwDeletePost(){
        Response response=given()
                .when().request("delete", "https://jsonplaceholder.typicode.com/posts/102");
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals("()",response.asString());
    }

    @Test
    public void pathParam(){
        Response response=given()
                .pathParam("userId","8")
                .when().request("get", "https://jsonplaceholder.typicode.com/users/{userId}");

        int id=response.body().jsonPath().getInt("id");
        String username=response.body().jsonPath().getString("username");
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(8,id);
        Assert.assertEquals("Maxime_Nienow",username);

    }
    @Test
    public void getQueryParam(){
        Response response=given()
                .queryParam("name", "Leanne Graham")
                .when().request("get", "https://jsonplaceholder.typicode.com/users");
        response.prettyPrint();
        response.then().body("$", Matchers.hasSize(1));
        int id=response.body().jsonPath().getInt("[0].id");
        System.out.println(id);

    }







}
