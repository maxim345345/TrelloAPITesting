package org.max.bm_parts;


import com.opencsv.CSVReader;
import io.restassured.response.Response;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

import java.time.LocalDateTime;
import java.util.*;


import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.max.bm_parts.Helper.trimSpace;
import static org.max.bm_parts.Helper.trimSpaceAndDota;


public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "main-search-field")
    WebElement searchWindow;

    @FindBy(className = "main-search-container__result-nav__link")
    WebElement showAll;

    @FindBy(how = How.CSS, using = ".mat-menu-trigger.button-cart-selected")
    WebElement busket;

    @FindBy(how = How.CSS, using = ".col-12.products-list__item")
    List<WebElement> allDetails;

    @FindBy(how = How.CSS, using = ".col-12.products-list__item")
    WebElement allDetailselement;

    @FindBy(how = How.CSS, using = ".btn.btn-success")
    WebElement clearFilter;


    public void searchWindow1(String artikul) throws InterruptedException {
        try {
            Thread.sleep(2000);
            System.out.println("the search start");
            wait.until(ExpectedConditions.elementToBeClickable(searchWindow)).clear();
            wait.until(ExpectedConditions.elementToBeClickable(searchWindow)).sendKeys(artikul);
            new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(showAll)).click();
        } catch (TimeoutException | StaleElementReferenceException e) {
            NonFoundedData nonFoundedData = new NonFoundedData(0, artikul, "SNR",
                    "https://b2b.bm.parts/", LocalDateTime.now());
            MainPage.loadDataToNonFoundedDB(nonFoundedData);
            e.printStackTrace();;
        }
//        if (showAll != null) {showAll.click();;}
//        else{ System.out.println(artikul+" is absent today");}
    }

    public boolean isOnPage() {
        return busket != null && busket.getAttribute("class").contains("button-cart-selected");
    }


    public List<OpponentPrice> collectDataFromSite1(String artikul) {

        List<OpponentPrice> artikulsAsObjects = new ArrayList<>();
        double[] currency = getUSDandEuroExchangeRate();

        try {
            for (WebElement a:allDetails) {
                new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class).
                        until(ExpectedConditions.elementToBeClickable(a));
            }
            List<String> listOfStringData = allDetails.stream().map(element ->
                    element.getAttribute("innerText")).collect(Collectors.toList());
            for (String element : listOfStringData) {
                String[] artikulAndPrice = getDataAsSeparateStrings(element);
                artikulAndPrice[0] = trimSpaceAndDota(artikulAndPrice[0]);
                artikulAndPrice[2] = trimSpace(artikulAndPrice[2]);

                if (artikulAndPrice[0].startsWith(artikul)) {
                    double price = Double.parseDouble(artikulAndPrice[2]);
                    OpponentPrice art = new OpponentPrice(0, site, artikulAndPrice[0], artikulAndPrice[1], price, currency[1], currency[0], LocalDateTime.now());
                    artikulsAsObjects.add(art);
                    System.out.println(art.getSkuNumber() + " is added");

                }
            }
        } catch
        (TimeoutException | StaleElementReferenceException e) {
            NonFoundedData nonFoundedData = new NonFoundedData(0, artikul, "SNR",
                    "https://b2b.bm.parts/", LocalDateTime.now());
            MainPage.loadDataToNonFoundedDB(nonFoundedData);
            e.printStackTrace();;
        }

        return artikulsAsObjects;
    }

    public List<OpponentPrice> collectDataFromSite(String artikul) {

        List<OpponentPrice> artikulsAsObjects = new ArrayList<>();
        double[] currency = getUSDandEuroExchangeRate();

        try {

                new WebDriverWait(driver, 5).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(allDetailselement));

            String stringData = allDetailselement.getAttribute("innerText");

                String[] artikulAndPrice = getDataAsSeparateStrings(stringData);
                artikulAndPrice[0] = trimSpaceAndDota(artikulAndPrice[0]);
                artikulAndPrice[2] = trimSpace(artikulAndPrice[2]);

                if (artikulAndPrice[0].startsWith(artikul)) {
                    double price = Double.parseDouble(artikulAndPrice[2]);
                    OpponentPrice art = new OpponentPrice(0, site, artikulAndPrice[0], artikulAndPrice[1], price, currency[1], currency[0], LocalDateTime.now());
                    artikulsAsObjects.add(art);
                    System.out.println(art.getSkuNumber() + " is added");

                }
            }
        catch
        (TimeoutException | StaleElementReferenceException e) {
            NonFoundedData nonFoundedData = new NonFoundedData(0, artikul, "SNR",
                    "https://b2b.bm.parts/", LocalDateTime.now());
            MainPage.loadDataToNonFoundedDB(nonFoundedData);
            e.printStackTrace();;
        }

        return artikulsAsObjects;
    }

    public String[] getDataAsSeparateStrings(String a) {
        String[] words = a.split("\n");

        return new String[]{words[0], words[1], words[words.length - 1]};
    }


    public static void loadDataToOutPutDB(List<OpponentPrice> opponentPrices) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "root")) {

            for (OpponentPrice element : opponentPrices) {
                String query = "insert into  output_data(pageName,skuNumber, brandName, price,euroAveCourse,usdAveCourse,created) values (?,?,?,?,?,?,?);";
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, element.getPageName());
                statement.setString(2, element.getSkuNumber());
                statement.setString(3, element.getBrandName());
                statement.setDouble(4, element.getPrice());
                statement.setDouble(5, element.getEuroAveCourse());
                statement.setDouble(6, element.getUsdAveCourse());

                Timestamp created = Timestamp.valueOf(element.getCreated());
                statement.setTimestamp(7, created);
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void loadDataToNonFoundedDB(NonFoundedData nonFoundedData) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "root")) {

            String query = "insert into  non_founded_sku_numbers(skuNumber, brandName, pageName,created) values (?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query);


            statement.setString(1, nonFoundedData.getSkuNumber());
            statement.setString(2, nonFoundedData.getBrandName());
            statement.setString(3, nonFoundedData.getPageName());

            Timestamp created = Timestamp.valueOf(nonFoundedData.getCreated());
            statement.setTimestamp(4, created);
            statement.executeUpdate();
            statement.close();

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static double[] getUSDandEuroExchangeRate() {
        String url = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";

        Response response =
                given()
                        .when()
                        .get(url)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        double[] usdAndEuro = new double[2];
        usdAndEuro[0] = ((response.jsonPath().getDouble("buy[0]") + response.jsonPath().getDouble("sale[0]")) / 2);
        usdAndEuro[1] = ((response.jsonPath().getDouble("buy[1]") + response.jsonPath().getDouble("sale[1]")) / 2);
        return usdAndEuro;
    }

//    public static List<InputData> CSVreader() throws IOException {
//        CSVReader reader = new CSVReader(new FileReader("d:/data.csv"), ',' , '"' , 1);
//
//        String[] nextLine;
//        while ((nextLine = reader.readNext()) != null) {
//            if (nextLine != null) {
//                //Verifying the read data here
//                System.out.println(Arrays.toString(nextLine));
//            }
//        }return
}
