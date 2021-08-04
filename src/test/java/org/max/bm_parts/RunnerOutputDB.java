package org.max.bm_parts;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RunnerOutputDB {

    public static WebDriver driver;
    static int countOfParameterizedTests;
    static List<String> list;
    WorkingWithInputData workingWithInputData;
    static int countOfRepeats;

    @BeforeAll
    public static void init() throws InterruptedException, SQLException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Иван\\IdeaProjects\\TrelloAPITesting\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WorkingWithInputData workingWithInputData = new WorkingWithInputData();

        int s = 70 * countOfRepeats;
        System.out.println(s+"skip");

         list = workingWithInputData.getAllObjects().stream().map(element -> element.getSkuNumber()).skip(s).limit(70).collect(Collectors.toList());
    }

    @AfterAll
    public static void after() {

       //driver.close();
        countOfParameterizedTests = 0;
        countOfRepeats += 1;
        System.out.println(countOfRepeats+"countOfRepeat");
    }
    @Test
    public void loadData() {


    }
    @Test

    public void autorization() throws InterruptedException {

        LoginPage loginPage = new LoginPage("https://b2b.bm.parts", driver);
        String login = "000007429";
        String password = "bbcab148";
        Assertions.assertEquals("Сервис авторизации", driver.getTitle());
        loginPage.sendLogin(login);
        loginPage.sendPassword(password);
        loginPage.enterButton();
        Assertions.assertEquals("BM Parts | B2B", driver.getTitle());

    }
    
    @ParameterizedTest
    //@ValueSource(strings = {"KD45549","ACB35X50X20"})
    // @CsvFileSource(resources = "/data.csv")
     @MethodSource ("priceProvider")

    public void search(String artikul) throws InterruptedException {
        //assumeTrue(countOfParameterizedTests < 100);
        MainPage mainPage = new MainPage(driver);
      //  try {
          //  List<InputData> dataFromOuterSearch=
            mainPage.searchWindow1(artikul);
            List<OpponentPrice> dataReadyForDownloding = mainPage.collectDataFromSite(artikul);
            MainPage.loadDataToOutPutDB(dataReadyForDownloding);
//        } catch (NoSuchElementException | StaleElementReferenceException x
//        ) {
//            System.out.println(x);
//        }
        countOfParameterizedTests += 1;
        System.out.println(countOfParameterizedTests);

    }

    public static Stream<String> priceProvider() {
        return list.stream();
    }
}
//TODO Input data save only unical SKU
//просканировать папку на наличие СSV файлов

     // Non-found Table id SKU date site