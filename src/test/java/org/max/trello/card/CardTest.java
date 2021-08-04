package org.max.trello.card;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.max.trello.Card;
import org.max.trello.Endpoints;

import java.text.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.max.trello.helpers.TrelloHelper.getCardId;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardTest {

    private String url = "https://trello.com";
    private RequestSpecification spec;

    @BeforeEach
    public void init() {

        spec =
                given()
                        .baseUri(url)
                        .queryParam("key", "e5f3210fa3e29f6df6c25a1cf9caf190")
                        .queryParam("token", "f1a139e691d8d880ab3318a3f1100d05851d6093371950f75797846d6d403144")
                        .contentType(ContentType.JSON);

    }

    @Test
    @Order(1)
    public void createTrelloCardTest() throws ParseException {


        System.out.println(spec);
        Response some = RestAssured
                .given()
                .spec(spec)
                .queryParam("idList", "600c7fdd3e6c7666b717a1d7")
                .queryParam("name", "New6Card")
                .queryParam("desc", "new training card")
                .queryParam("due", new SimpleDateFormat("y-MM-dd HH:mm").parse("2021-02-11 13:28"))

                .when()
                .post(Endpoints.CREATE_CARD)

                .then()
                .statusCode(200)
                .and()
                .body("desc", equalTo("new training card"))

                .extract().response();

       // String a=some.

    }

  /*  @Test
    @Order(2)
    public void updaitTrelloCardTest() throws ParseException {


        given()
                .baseUri(url)
                .queryParam("key", "e5f3210fa3e29f6df6c25a1cf9caf190")
                .queryParam("token", "f1a139e691d8d880ab3318a3f1100d05851d6093371950f75797846d6d403144")
                .pathParam("id", getCardId("Last2NewCard", "copy"))
                .contentType(ContentType.JSON)
                .queryParam("name", "Last3NewCard")
               // .queryParam("desc", "last new training card")
               // .queryParam("due", new SimpleDateFormat("y-MM-dd HH:mm").parse("2021-02-13 13:28"))

                .when()
                .put(Endpoints.UPDATE_CARD)

                .then()
                .statusCode(200)
               .and()
               .body("name", equalTo("Last3NewCard"));*/




    @Test
    @Order(3)
    public Card getTrelloCardTest() {
        RequestSpecification spec =
                given()
                        .baseUri(url)
                        .queryParam("key", "f848f2906461a0e6961f6fd444b34266")
                        .queryParam("token", "fff62a9d38dfc884da4ca24b0405268ee7e1d03a7c41c2b095e4f0d2715b99cb")
                        .contentType(ContentType.JSON);


        Card some = RestAssured
                .given()
                .spec(spec)
                .pathParam("id", getCardId("newdesk2", spec))
                .contentType(ContentType.JSON)


                .when()
                .get(Endpoints.GET_CARD)

                .then()
                .statusCode(200)

                .extract().as(Card.class);
        System.out.println(some);
       // assertThat(some.getName()).as("LastNewCard").isEqualTo("LastNew5Card");
    return some;}
/*

    @Test

  public void deleteTrelloCardTest() {


        Card some = RestAssured
                .given()
                .baseUri(url)
                .queryParam("key", "e5f3210fa3e29f6df6c25a1cf9caf190")
                .queryParam("token", "f1a139e691d8d880ab3318a3f1100d05851d6093371950f75797846d6d403144")
                .pathParam("id", getCardId("LastNewCard", "copy"))
                .contentType(ContentType.JSON)


                .when()
                .delete(Endpoints.DELETE_CARD)

                .then()
                .statusCode(200)
                .extract().as(Card.class);


    }*/
}