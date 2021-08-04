package org.max.trello.board;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.max.trello.Endpoints;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {
    private String url = "https://api.trello.com";


    @Test
    public void getTrelloBoardTest(RequestSpecification spec) {


      Board some=RestAssured
                .given()
                .baseUri(url)
                .contentType(ContentType.JSON)
                .queryParam("key", "e5f3210fa3e29f6df6c25a1cf9caf190")
                .queryParam("token", "f1a139e691d8d880ab3318a3f1100d05851d6093371950f75797846d6d403144")
                .queryParam("name", "desk1")
                .pathParam("id", "5f44e9f5b80c801aff9c6291")
                // .queryParam("lists", "all")

                .when()
                .get(Endpoints.GET_BOARD)

                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)

              .extract().as(Board.class);


        System.out.println(some);

        assertThat(some.getName()).as("desk1").isEqualTo("desk1");
    }
   /* {      Board some1=RestAssured
            .given()
            .baseUri(url)
            .contentType(ContentType.JSON)
            .queryParam("key", "e5f3210fa3e29f6df6c25a1cf9caf190")
            .queryParam("token", "f1a139e691d8d880ab3318a3f1100d05851d6093371950f75797846d6d403144")

            .pathParam("id", "6008469c371c302d7d0c1b3f")

            .when()
            .delete(Endpoints.GET_CARD)

            .then()
            .statusCode(200)
            .and()
            .contentType(ContentType.JSON)
            .extract().as(Board.class);

        System.out.println(some1);}
    public static void createCard(String cardName, String boardName, String listName) {

        Response res = given()
                .baseUri(EndPoints.baseURL)
                .queryParam("name", cardName)
                .queryParam("key", EndPoints.key)
                .queryParam("token", EndPoints.token)
                .queryParam("idList", getListId(boardName, listName))
                .when()
                .post(EndPoints.createCard)
                .then()
                .statusCode(200)
                .extract().response();

    }*/

}
