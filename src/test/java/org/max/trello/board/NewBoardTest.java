package org.max.trello.board;

import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.max.trello.Endpoints;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class NewBoardTest {
    private String url = "https://trello.com";

    private RequestSpecification spec;

    @Before

    public void init() {

        spec =
                given()
                        .baseUri(url)
                        .queryParam("key", "e5f3210fa3e29f6df6c25a1cf9caf190")
                        .queryParam("token", "f1a139e691d8d880ab3318a3f1100d05851d6093371950f75797846d6d403144")
                        .contentType(ContentType.JSON);

    }

    @Test
    public void getTrelloBoardTest() {


        String id =
                given()
                        .spec(spec)
                        .when()
                        .get(Endpoints.GET_MY_BOARDS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .path("find{it.name==' отпуск'}.name");


        System.out.println(id);

    }

    @Test
    public void getTrelloBoardTest1() {


        String id =
                given()
                        .baseUri(url)
                        .queryParam("key", "e5f3210fa3e29f6df6c25a1cf9caf190")
                        .queryParam("token", "f1a139e691d8d880ab3318a3f1100d05851d6093371950f75797846d6d403144")
                        .contentType(ContentType.JSON)
                        .when()
                        .get(Endpoints.GET_MY_BOARDS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .path("find{it.name=='%s'}.id", "copy");


        System.out.println(id);

    }

    @Test
    public void getMapFromRespondTest() {


        Map<String, ?> boardInfo =
                given()
                        .baseUri(url)
                        .queryParam("key", "e5f3210fa3e29f6df6c25a1cf9caf190")
                        .queryParam("token", "f1a139e691d8d880ab3318a3f1100d05851d6093371950f75797846d6d403144")
                        .contentType(ContentType.JSON)
                        .when()
                        .get(Endpoints.GET_MY_BOARDS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .path("find{it.name=='%s'}", " отпуск");


        boardInfo.forEach((key, value) -> System.out.printf("%20s\t%s\n", key, value));
//        System.out.println(boardInfo);

    }

}