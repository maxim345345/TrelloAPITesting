package org.max.trello.selenium;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.max.trello.helpers.TrelloHelper.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class SeleniumTest {
    @Test

    @Order(0)

    public WebDriver trelloAutorization() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Иван\\IdeaProjects\\TrelloAPITesting\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();

        driver.get("http://trello.com");

        List<WebElement> elements = driver.findElements(By.tagName("a"));

        //System.out.println(elements.size());

        //elements.forEach(we -> System.out.println(we.getText()));

        List<WebElement> finded = elements.stream().filter(element -> element.getText().equals("Войти")).collect(Collectors.toList());

        System.out.println(finded.size());
        finded.get(0).click();


        WebElement googleButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("googleButton")));
        googleButton.click();


        WebElement googleEmailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identifier")));
        googleEmailInput.sendKeys("fosset9999@gmail.com");

        driver.findElement(By.className("VfPpkd-RLmnJb")).click();

        WebElement passwordBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        passwordBlock.sendKeys("kingkong88");
        WebElement passwordBlockedn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordNext")));
        passwordBlockedn.click();
        return driver;
    }

    //elements.forEach(we -> System.out.println(we.getText()));
    @Test
    public void createDesk() throws InterruptedException {

        WebDriver driver = trelloAutorization();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement boardsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("MEu8ZECLGMLeab")));
        boardsButton.click();

        WebElement createButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search-boards")));
        createButton.sendKeys("newdesk1");
        WebElement createButtoneded = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bHF9FML4QxCHFM")));
        createButtoneded.click();
        WebElement createDesk = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("uj9Ovoj4USRUQz")));
        createDesk.click();
        WebElement mainMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sc-bdVaJa")));
        mainMenu.click();
        //List<WebElement> elementsNew = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("p")));
//        // List<WebElement> findeded = elementsNew.stream().filter(element -> element.getText().equals("Создать доску")).collect(Collectors.toList());
//        //finded.get(0).click();
////        driver.findElement(By.className("boards-page-board-section-list-item")).click();
    }

    @Test
    public void addList() throws InterruptedException {

        WebDriver driver = trelloAutorization();

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement boardsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("MEu8ZECLGMLeab")));
        boardsButton.click();

        WebElement createButton = driver.findElement(By.name("search-boards"));
        createButton.sendKeys("newdesk2");

        Actions builder = new Actions(driver);
        Action mousemove = builder.moveToElement(createButton, 50, 50).click().build();
        mousemove.perform();
        Thread.sleep(2000);


        /*WebElement addListButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Добавить список")));
        addListButton.click();

        WebElement addListName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Ввести заголовок списка']")));
        addListName.sendKeys("addList");

        WebElement addListFinal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[value='Добавить список']")));
        addListFinal.click();

        WebElement closeNewWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("icon-lg")));
        closeNewWindow.click();*/
    }

    @Test
    public void deleteDesk() throws InterruptedException {

        WebDriver driver = trelloAutorization();

        WebDriverWait wait = new WebDriverWait(driver, 20);


        // WebElement boardsButton4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-test-id='home-team-tab-name']")));
        // boardsButton4.click();
        List<WebElement> elementsNew2 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span[data-test-id='home-team-tab-name']")));
        List<WebElement> findeded2 = elementsNew2.stream().filter(element -> element.getText().equals("team")).collect(Collectors.toList());
        findeded2.get(0).click();

        List<WebElement> elementsNew1 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("_3qwe2tMMFonNvf")));
        List<WebElement> findeded1 = elementsNew1.stream().filter(element -> element.getText().equals("Доски")).collect(Collectors.toList());
        findeded1.get(1).click();

        /*WebElement boardsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("MEu8ZECLGMLeab")));
        boardsButton.click();

        WebElement boardsButton1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[class*=_2cXFTH_8wo4pyK]")));
        boardsButton1.click();

        WebElement boardsButton2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.boards-page-board-section-list li:nth-of-type(2)")));
        boardsButton2.click();*/

        List<WebElement> desks=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("boards-page-board-section-list-item")));
        System.out.println(desks.size());
        desks.get(1).click();


        // WebElement createButton = driver.findElement(By.name("search-boards"));
        // createButton.sendKeys("newdesk");

        // Actions builder = new Actions(driver);
        // Action mousemove = builder.moveToElement(createButton, 50, 50).click().build();
        // mousemove.perform();
        // Thread.sleep(2000);
         WebElement menu = driver.findElement(By.linkText("Меню"));
         menu.click();

        List<WebElement> elementss=driver.findElements(By.tagName("a"));
        Thread.sleep(2000);
         List<WebElement> find=elementss.stream().filter(element->element.getAttribute("class").equals("board-menu-navigation-item-link")).collect(Collectors.toList());
        find.get(0).click();
          Thread.sleep(2000);
        WebElement anotherButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/div[2]/div/ul[1]/li[5]/a"));
        anotherButton.click();
         WebElement menu1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Ещё")));
         menu1.click();

         WebElement menu2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Закрыть доску…")));
         menu2.click();


        // WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[value='Закрыть']")));
        //close.click();
        /*List<WebElement> elementsss=driver.findElements(By.tagName("input"));
         List<WebElement> findeded=elementsss.stream().filter(element->element.getAttribute("value").equals("Закрыть")).collect(Collectors.toList());
        findeded.get(0).click();*/


    }


    @Test
    public void boardID() throws InterruptedException {
        String url = "https://trello.com";
        RequestSpecification spec =
                given()
                        .baseUri(url)
                        .queryParam("key", "f848f2906461a0e6961f6fd444b34266")
                        .queryParam("token", "fff62a9d38dfc884da4ca24b0405268ee7e1d03a7c41c2b095e4f0d2715b99cb")
                        .contentType(ContentType.JSON);


        String boardId = getBoardId("newdesk2", spec);
        System.out.println(boardId);
        String listId = getListId("listing", "newdesk2", spec);
        System.out.println(listId);
        ArrayList<String> cardIds = getSpecificCardId("newdesk2", spec);
        System.out.println(cardIds.size());
        System.out.println(cardIds);

        WebDriver driver = trelloAutorization();

        WebDriverWait wait = new WebDriverWait(driver, 20);

        WebElement boardsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("MEu8ZECLGMLeab")));
        boardsButton.click();

        WebElement createButton = driver.findElement(By.name("search-boards"));
        createButton.sendKeys("newdesk2");

        Actions builder = new Actions(driver);
        Action mousemove = builder.moveToElement(createButton, 50, 50).click().build();
        mousemove.perform();
        WebElement aa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(cardIds.get(0))));
        System.out.println(aa.getText());

        // List<WebElement> cards=new ArrayList<WebElement>();
        // for (int i = 0; i <cardIds.size() ; i++) {
        //    cards.add(driver.findElement(By.id(cardIds.get(i))));
        // }
        // cards.forEach(we -> System.out.println(we.getAttribute("class")));
        //return boardId;
    }

    @Test


    public void testing() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Иван\\IdeaProjects\\TrelloAPITesting\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://sport.ua");


        // driver.get("https://football.ua");
        // driver.navigate().back();
        // String title=driver.getTitle();
        //System.out.println(title);
        // String handle=driver.getWindowHandle();
        // System.out.println(handle);
        // driver.close();
        // driver.quit();
        /*public void moveCards (String headerText, String listNameOne, String listNameTwo) throws InterruptedException {
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // cardsToMove.forEach(c -> System.out.println(c.getText()));
            for (WebElement element : cardsToMove) {
                element.click();
                Thread.sleep(7000);
                WebElement headerDueDate = driver.findElement(By.className("card-detail-badge-due-date-button"));

                if (headerDueDate.getText().toLowerCase().endsWith(headerText)) {
                    moveButton.click();
                    Thread.sleep(4000);
                    //   WebElement moveCardWindow = driver.findElement(By.className("no-back"));
                    Select listSelect = new Select(driver.findElement(By.className("js-select-list")));
                    listSelect.selectByVisibleText(listNameOne);
                    Thread.sleep(3000);
                    moveToAnotherCardButton.click();
                    Thread.sleep(3000);
                    Assert.assertTrue(isCardMovedToNeededList(listNameOne));
                    closeCardButton.click();
                } else {
                    moveButton.click();
                    Thread.sleep(4000);
                    Select listSelect = new Select(driver.findElement(By.className("js-select-list")));
                    listSelect.selectByVisibleText(listNameTwo);
                    Thread.sleep(3000);
                    moveToAnotherCardButton.click();
                    Thread.sleep(3000);
                    Assert.assertTrue(isCardMovedToNeededList(listNameTwo));
                    closeCardButton.click();
                }

    }*/
    }
}