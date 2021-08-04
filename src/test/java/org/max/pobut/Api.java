package org.max.pobut;

import io.cucumber.java.bs.A;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


    public class Api {
    private String url = "https://api.avtoit.com/tecdoc/";
    private String apiId = "vvc14bk29f92c4560890ed291434d1d73cf24731";
    private String apiKey = "tf65co93cd42f1aee5f865569dd7ab0d000763";

    private String Brand = "wizzard/brand-info/";
    private String Models = "wizzard/models/";
    private String Vehicles = "wizzard/vehicles/";
    private String findByArticle = "find/article/";
    private String VehiclesTypes = "wizzard/vehiclestypes/";

    public Api() {
    }

    public void getAllV() {

        Response response = RestAssured.given()
                .baseUri(url)
                .queryParam("apiId", apiId)
                .queryParam("apiKey", apiKey)
                .contentType(ContentType.JSON)
                .when()
                .get(VehiclesTypes)
                .then()
                .statusCode(200)

                .extract().response();
        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
       assertEquals(statusCode, 200);
        String s = response.getBody().asString();
        System.out.println(s);

        //.jsonPath().getObject("VehiclesTypes", VehiclesTypes.class);

        // System.out.println(vehiclesTypes);

    }

    public Brand getAllBrand(int vehiclesTypeId, int brandId) {

        Brand brandEntity = RestAssured.given()
                .baseUri(url)
                .queryParam("apiId", apiId)
                .queryParam("apiKey", apiKey)
                .queryParam("vehiclesTypeId", vehiclesTypeId)
                .queryParam("brandId", brandId)
                .contentType(ContentType.JSON)
                .when()
                .get(Brand)
                .then()

                .extract()
                .jsonPath().getObject("Brand", Brand.class);

             assertNotNull(brandEntity);
             assertThat(brandEntity.getBrandId(), equalTo(brandId));
            // assertThat(brandEntity.getV), equalTo(brandId));


        System.out.println(brandEntity.toString());
        return brandEntity;
    }


    public List<Model> getModels(int vehiclesTypeId, int brandId, int year) {

        List<Model> models = RestAssured.given()
                .baseUri(url)
                .queryParam("apiId", apiId)
                .queryParam("apiKey", apiKey)
                .queryParam("vehiclesTypeId", vehiclesTypeId)
                .queryParam("brandId", brandId)
                .queryParam("year", year)
                .contentType(ContentType.JSON)
                .when()
                .get(Models)
                .then()

                .extract()
                .jsonPath().getList("Models", Model.class);
        models.stream().forEach(model -> model.setBrandId(brandId));
        models.stream().forEach(model -> System.out.println(model));

        return models;
    }


    public List<VehicleModification> getVehicles(int vehiclesTypeId, int brandId, int modelId, int year) {

        List<VehicleModification> vehicles = RestAssured.given()
                .baseUri(url)
                .queryParam("apiId", apiId)
                .queryParam("apiKey", apiKey)
                .queryParam("vehiclesTypeId", vehiclesTypeId)
                .queryParam("brandId", brandId)
                .queryParam("modelId", modelId)
                .queryParam("year", year)
                .contentType(ContentType.JSON)
                .when()
                .get(Vehicles)
                .then()
                .extract()
                .jsonPath().getList("Vehicles", VehicleModification.class);

        vehicles.stream().forEach(vehicle -> vehicle.setModelId(modelId));

        return vehicles;
    }


//        public List<PartEntity> getPartsByArticle(int type, String article, boolean searchExact) {
//
//            List<PartEntity> partEntities = given()
//                    .baseUri(url)
//                    .queryParam("apiId", apiId)
//                    .queryParam("apiKey", apiKey)
//                    .queryParam("type", type)
//                    .queryParam("article", article)
//                    .queryParam("searchExact", searchExact)
//                    .contentType(ContentType.JSON)
//                    .when()
//                    .get(findByArticle)
//                    .then()
//                    .extract()
//                    .jsonPath().getList("Article", PartEntity.class);
//
//            return partEntities;
//        }

    public static void main(String[] args) {
        Api a = new Api();
        // a.getAllV();
        a.getAllBrand(1, 25);
       // a.getModels(1,25,2015);
    }
}

