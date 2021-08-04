package org.max.Rozetka;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class PageObject {

    public static WebDriver driver;


    @BeforeAll

    public static void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Иван\\IdeaProjects" +
                "\\TrelloAPITesting\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://rozetka.com.ua/ua/");
    }

    @Test
    public void holodilnikiRozetka() throws InterruptedException, IOException {

        MainPage main= new MainPage(driver);
        main.searchWindow();
        main.setModelChoice();
       // main.listOfTitles();
       // main.listOfPrices();
       // main.fileIO(main.listOfPrices());
       // main.toExcel(main.listOfTitles(),main.listOfPrices());
        //System.out.println(main.listOfTitles().size());
       // System.out.println(main.listOfPrices().size());
        main.allGoods();
       // driver.close();

    }
}