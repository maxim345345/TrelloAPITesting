package org.max;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;


public class FootballData {

    @BeforeClass
    public static void setupRestAssured() {
        RestAssured.baseURI = "http://api.football-data.org";
        RestAssured.basePath = "/v2/";
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                addHeader("X-Auth-Token", "6ea19932acd545a7a82b1dfdadb65a0c").
                addHeader("X-Response-Control", "minified")
                .build();
        RestAssured.requestSpecification = requestSpecification;
    }

    @Test
    public void extractSingleValue_findSingleTeamName() {
        Response response = get("teams/1");
        String teamName = response.path("website");
        System.out.println(teamName);
    }


@Test
public void extractSingleValueWithFind_findAPlayerWithACertainJerseyNumber(){

        Response response = get("teams/1");
    List<String> as = response.path("players.findAll { it.position == 'Attacker' }.name");
    System.out.println(as);
}

}