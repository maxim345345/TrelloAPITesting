package org.max.trello.model;

import io.cucumber.java.bs.A;
import org.assertj.core.api.Assertions;
import org.junit.Assert;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PageObjectTest {
    public static WebDriver driver;

    @BeforeAll
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Иван\\IdeaProjects\\TrelloAPITesting\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @Order(1)
    public void autorization() throws InterruptedException {

        //GIVEN
        StartPage page = new StartPage("http://trello.com", driver);
        LoginPage loginPage = new LoginPage(driver);
        String email = "fosset9999@gmail.com";


        //WHEN
        page.clickSignInButton();
        Assertions.assertThat(loginPage.isOnPage());

        loginPage.sendAdress(email);
        loginPage.comeInAtlasianButton();
        loginPage.sendPassword();

        loginPage.comeInButton().click();

        //loginPage.clickGoogleButton();

        // loginPage.sendGoogleAdress(email);
        //loginPage.clicknextButton();

        //loginPage.sendGooglePassword();
        //loginPage.clicknextButton();
        loginPage.accountButton();


        //THAN
        // Assert.assertEquals(email, loginPage.getEmail());
        //driver.navigate().back();
    }

    /* @ParameterizedTest
      @MethodSource("stringProvider")
      //@CsvSource({"1, second", "1,team"})
      //@ValueSource(strings = {"second", "team"})
      @Order(2)
      public void createBoard(String team) throws InterruptedException {


          MainMenu menu = new MainMenu(driver);

          menu.selectTeam(team).click();
          menu.teamBoards().get(1).click();

          Thread.sleep(2000);
          final int numberOfBoards = menu.boards.size();


          menu.aLLBoards();
          menu.namedNewBoard();
          Thread.sleep(2000);
          driver.navigate().back();
          driver.navigate().back();


          menu.selectTeam(team).click();

          Assert.assertEquals(numberOfBoards + 1, menu.boards.size());
      }

      static Stream<String> stringProvider() {

          FileReading file = new FileReading("D:/temp.txt");
          List<String> words = file.splitIntoWords();


          return Stream.of(words.get(0), words.get(2));
      }*/
   /*@Test
    @Order(2)
    public void boardLifeCycleTest() throws InterruptedException {


        MainMenu menu = new MainMenu(driver);
        BoardPage boardpage = new BoardPage(driver);


        String boardName = "newboard";
        String newBoardName = "newboard111";
        String listName = "newlist";
        String cardName = "newcard";


        menu.createNewBoard(boardName);
        boardpage.createList(listName);
        Assert.assertTrue(boardpage.isListCreated(listName));
        boardpage.createCard(cardName);
        Assert.assertTrue(boardpage.isCardtCreated(cardName));
        boardpage.deleteCard();
        boardpage.deleteList();
        boardpage.renameBoard(newBoardName);
        Assert.assertTrue(boardpage.isBoardRenamed(newBoardName));
        menu.deleteNewBoard();

    }*/
   /* @ParameterizedTest
    @ValueSource(strings = {"newboard111"})
    public void cardsMoving(String name) throws InterruptedException {

        MainMenu menu = new MainMenu(driver);
        BoardPage boardpage = new BoardPage(driver);
        String listToMoving = "tt";

        menu.openBoard(name);

        for (WebElement element : boardpage.allCards
        ) {
            boardpage.moveCard(listToMoving);
        }
    }*/
//    @ParameterizedTest
//    @ValueSource(strings = {"newboard111"})
//    public void cardsWithColorLabelMoving1(String name) throws InterruptedException {
//
//        MainMenu menu = new MainMenu(driver);
//        BoardPage boardpage = new BoardPage(driver);
//        String listToMoving = "redlist";
//
//        menu.openBoard(name);
//        List<WebElement> allcardslist = boardpage.allCards;
//        System.out.println(allcardslist.size());
//        for (WebElement element : allcardslist
//        ) {
//
//            boardpage.moveColoredCard(listToMoving, allcardslist.indexOf(element));
//
//        }
//
//    }
//        }

    @ParameterizedTest
    @ValueSource(strings = {"newboard111"})
    public void cardsWithColorLabelMoving2(String name) throws InterruptedException {

        MainMenu menu = new MainMenu(driver);

        String listToMoving = "redlist";
        menu.openBoard(name);
        BoardPage boardpage = new BoardPage(driver);
       // List<WebElement> allcardslist = boardpage.allCards;
    //    System.out.println(allcardslist.size());

       // final List<String> names = boardpage.collectNamesOfCards();

        for (int i = 0; i <boardpage.allCards.size() ; i++) {
            boardpage.moveColoredCard(listToMoving,i);
        }


    }
}
   /* @ParameterizedTest
    @ValueSource(strings = {"newboard111"})
    public void cardsSorting(String name) throws InterruptedException {

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
}*/



   /* @ParameterizedTest
    //@MethodSource("stringProvider")
    //@CsvSource({"1, second", "1,team"})
    @ValueSource(strings = {"team"})
    @Order(2)
    public void boardsLimitTest(String team) throws InterruptedException {


        MainMenu menu = new MainMenu(driver);

        menu.selectTeam(team).click();
        menu.teamBoards().get(1).click();

        menu.aLLBoards();
        menu.createFinalBoard();


        Assert.assertTrue(menu.isPageOpened());
    }



  /*  @ParameterizedTest
    @MethodSource("stringProvider")

    @Order(3)
    public void createFewBoards(String team) throws InterruptedException {


        MainMenu menu = new MainMenu(driver);
        Thread.sleep(2000);
        menu.selectTeam(team).click();
        menu.teamBoards().get(1).click();
        Thread.sleep(2000);
        final int numberOfBoards = menu.boards.size();
        for (long i = 1; i <= 2; i++) {

            menu.aLLBoards();
            menu.namedNewBoard();

            driver.navigate().back();
            driver.navigate().back();
        }

        menu.selectTeam(team).click();

        Assert.assertEquals(2 + numberOfBoards, menu.boards.size());
    }

    static Stream<String> stringProvider() {
        return Stream.of("second", "team");
    }*/


   /* @ParameterizedTest
    @CsvSource({"team", "Board2"})
    @Order(5)
    public void createList(String team, String boardName) throws InterruptedException {


        MainMenu menu = new MainMenu(driver);
        Thread.sleep(2000);
        menu.selectTeam(team).click();
        menu.teamBoards().get(1).click();
        Thread.sleep(2000);
        System.out.println(menu.boardsByNames.size());
        menu.boardsByname(boardName).click();


        // menu.selectTeam(team).click();

        // Assert.assertEquals(number + numberOfBoards, menu.boards.size());
    }*/

   /* @ParameterizedTest
    @ValueSource(strings = {"team"})

    public void deleteAllBoard(String team) throws InterruptedException {

        MainMenu menu = new MainMenu(driver);

        menu.selectTeam(team).click();
        menu.teamBoards().get(1).click();
        Thread.sleep(2000);
        int numberOfBoards = menu.boards.size();
        menu.boards.stream().limit(numberOfBoards - 1).forEach(element -> {
            try {
                menu.deleteDesk(element);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        menu.selectTeam(team).click();
        Assert.assertEquals(1, menu.boards.size());
    }
}/*

//    @Test
//    public void cardsSorting() throws InterruptedException {
//        autorization();
//        MainMenu menu = new MainMenu(driver);
//        Thread.sleep(2000);
//        menu.openBoard();
//        System.out.println(menu.listOfCards.size());
//        List<WebElement> list = menu.listcards();
//        //List<WebElement> findeded1 = list.stream().filter(teamList -> teamList.getAttribute("class").contains("js-member-droppable")).collect(Collectors.toList());
//
//
//        // System.out.println(findeded1.size());
//        //findeded1.get(1).click();
//        // menu.teamList().get(0).click();
//        // menu.teamBoards().get(1).click();
//
//    }

*/
