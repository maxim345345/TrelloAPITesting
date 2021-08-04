package org.max.bm_parts;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RunnerInputDB {

    static private LoadingFromPobutPriceList loadingFromPobutPriceList;

    @BeforeAll
    public static void init() {

        loadingFromPobutPriceList = new LoadingFromPobutPriceList();
    }
    @Order(0)
    @Test
    public void loadData() {

        loadingFromPobutPriceList.loadPriceList("src/test/resources/data.csv");}


    @Order(1)
    @ParameterizedTest
    @MethodSource("priceProvider")

    public void downloadData(PriceList listItem) throws SQLException, NullPointerException {


        List<String> internalBrandName = loadingFromPobutPriceList.parseBrandName(listItem.getBrand(), " ");

        InputData inputData = new InputData();
        inputData.setSkuNumber(listItem.getSkuNumber());
        inputData.setBrandName(internalBrandName.get(0).toUpperCase());
        inputData.setCreated(LocalDateTime.now());
       loadingFromPobutPriceList.loadDataToInputDB(inputData);
    }


    public static Stream<PriceList> priceProvider() {
        return loadingFromPobutPriceList.getBasicData().stream();
    }
}
