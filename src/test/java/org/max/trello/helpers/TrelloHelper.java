package org.max.trello.helpers;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.max.trello.Endpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TrelloHelper {


    public static String getBoardId(String boardName, RequestSpecification spec) {

        //todo 1. send get request GET_MY_BOARDS
        //todo 2. confirm response structure
        //todo 3. find board id place
        //todo 4. create groovy query (boardName)
        String idBoard =
                given()
                        .spec(spec)
                        .when()
                        .get(Endpoints.GET_MY_BOARDS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .path("find{it.name=='%s'}.id", boardName);
        //  System.out.println(idBoard);

        return idBoard;
    }

    public static String getCardId(String boardName, RequestSpecification spec) {

        String idCard =
                given()
                        .spec(spec)
                        .pathParam("id", getBoardId(boardName, spec))
                        .when()
                        .get(Endpoints.GET_ALL_CARDS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                       .path("find{it.name=='%s'}.id", "kart1");


        return idCard;
    }

    public static ArrayList<String> getSpecificCardId(String boardName, RequestSpecification spec) {

        ArrayList<String> idCard =
                given()
                        .spec(spec)
                        .pathParam("id", getBoardId(boardName, spec))
                        .when()
                        .get(Endpoints.GET_ALL_CARDS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .path("findAll{it.name=='kart1'|| it.name=='kart2'}.id");


        return idCard;
    }

    public static String getListId(String listName, String boardName, RequestSpecification spec) {

        String idList =
                given()
                        .spec(spec)
                        .pathParam("id", getBoardId(boardName, spec))

                        .when()
                        .get(Endpoints.GET_ALL_LISTS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .path("find{it.name=='%s'}.id", listName);
        // System.out.println(idList);

        return idList;
    }

    public static String getCardField(String listName, String boardName, RequestSpecification spec) {
        String idList =
                given()
                        .spec(spec)
                        .pathParam("id", getBoardId(boardName, spec))

                        .when()
                        .get(Endpoints.GET_ALL_LISTS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .path("find{it.name=='%s'}.id", listName);
        // System.out.println(idList);

        return idList;
    }
}