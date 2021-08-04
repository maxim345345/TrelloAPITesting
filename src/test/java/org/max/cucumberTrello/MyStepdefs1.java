package org.max.cucumberTrello;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.max.trello.model.BoardPage;
import org.max.trello.model.LoginPage;
import org.max.trello.model.MainMenu;
import org.max.trello.model.StartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.DriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MyStepdefs1 {
    private DriverManager manager;
    private WebDriver driver;
    // private MainMenu menu = new MainMenu(driver);


    @Given("I am in the Trello main menu")
    public void iAmInTheTrelloMainMenu() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Иван\\IdeaProjects\\TrelloAPITesting\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        StartPage page = new StartPage("http://trello.com", driver);
        LoginPage loginPage = new LoginPage(driver);
        String email = "fosset9999@gmail.com";

        page.clickSignInButton();
        loginPage.sendAdress(email);
        loginPage.comeInAtlasianButton();
        loginPage.sendPassword();
        loginPage.comeInButton().click();
        loginPage.accountButton();
    }


    @When("I want to sort cards in the list on the {string} board:")
    public void iWantToSortCardsInTheListOnTheBoardNameBoard(String name) throws InterruptedException {
        MainMenu menu = new MainMenu(driver);
        BoardPage boardpage = new BoardPage(driver);
        List<String> elementTeam1;


        menu.openBoard(name);
        List<WebElement> b = boardpage.allCards;

        final List<String> names = boardpage.collectNamesOfCards();
        do {

            for (WebElement element : b
            ) {
                for (int i = 0; i < names.size(); i++) {

                    if (element.getAttribute("outerText").equals(names.get(i))) {
                        boardpage.sortedCard(i, b.indexOf(element));
                    }
                }

            }

            elementTeam1 = boardpage.allCards.stream().map(element -> element.getAttribute("outerText")).
                    collect(Collectors.toList());

            System.out.println(elementTeam1);
        }
        while (!elementTeam1.equals(names));
    }

    @Then("The cards are sorted")
    public void theCardsAreSorted() throws InterruptedException {
        BoardPage boardpage = new BoardPage(driver);
        List<String> names = boardpage.collectNamesOfCardsWithoutSorting();
        Assertions.assertThat(names).isSorted();

    }
}

