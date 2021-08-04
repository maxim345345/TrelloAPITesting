package org.max.trello.board;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.max.trello.Endpoints;

import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.max.trello.helpers.TrelloHelper.getBoardId;
import static org.max.trello.helpers.TrelloHelper.getCardId;


class BoardTestTest {
    private String url = "https://api.trello.com";

    @Test
    public void getTrelloBoardTest() {



                given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .queryParam("key", "e5f3210fa3e29f6df6c25a1cf9caf190")
                .queryParam("token", "f1a139e691d8d880ab3318a3f1100d05851d6093371950f75797846d6d403144")

                .pathParam("id", "5f44e9f5b80c801aff9c6291")


                .when()
                .get("/1/boards/{id}")

                .then()
               .body("name", equalTo("desk1"));

    }
    @Test
    public void getTrelloTest() {



        given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .queryParam("key", "e5f3210fa3e29f6df6c25a1cf9caf190")
                .queryParam("token", "f1a139e691d8d880ab3318a3f1100d05851d6093371950f75797846d6d403144")

                .pathParam("id", "5f44e9f5b80c801aff9c6291")


                .when()
                .get("/1/boards/{id}")

                .then()
                .body("name", equalTo("desk1"));

    }
    @Test
    public void getTrelloBoardTest1() {



        String name=
                given()
                        .baseUri(url)
                        .queryParam("key","e5f3210fa3e29f6df6c25a1cf9caf190")
                        .queryParam("token","f1a139e691d8d880ab3318a3f1100d05851d6093371950f75797846d6d403144")
                        .pathParam("id", "600c7fdd3e6c7666b717a236")
                        .queryParam("fields", "idMembers,labels")
                        .contentType(ContentType.JSON)
                        .when()
                        .get(Endpoints.GET_CARD_FIELD)
                        .then()
                        .statusCode(200)
                        . extract()
                        .response()
//                .prettyPrint();
                    .path("labels.find{it.color=='%s'}.name", "yellow");

        System.out.println(name);

    }

    public static void main(String[] args) {

        String url = "https://trello.com";
        RequestSpecification spec;
        spec = given()
                .baseUri(url)
                .queryParam("key", "f848f2906461a0e6961f6fd444b34266")
                .queryParam("token", "fff62a9d38dfc884da4ca24b0405268ee7e1d03a7c41c2b095e4f0d2715b99cb")
                .contentType(ContentType.JSON);
       // Map<String,?> rr=getCardId("newdesk2",spec);
      //  rr.forEach((key, value) -> System.out.printf("%20s\t%s\n", key, value));
       //String idBoard=getBoardId("desk1");
      //  String idCard=getCardId("ADDITIONAL","desk1");
       // String idList=getListdId("DATA","copy");
       // System.out.println(idList);
    }
}

