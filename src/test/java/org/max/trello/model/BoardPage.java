package org.max.trello.model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.sort;

public class BoardPage extends BasePage {


    //переименовать борд
    @FindBy(how = How.CSS, using = "h1[dir='auto']")
    WebElement renameBoard;

    @FindBy(how = How.CSS, using = "input[class*=board-name-input]")
    WebElement renameBoard2;

    //создать список
    @FindBy(how = How.CLASS_NAME, using = "placeholder")
    WebElement createListButton;

    @FindBy(how = How.CSS, using = "input[placeholder='Ввести заголовок списка']")
    WebElement inputNameOfListButton;

    @FindBy(how = How.CSS, using = "input[value='Добавить список']")
    WebElement addListButton;

    //проверка созданного списка
    @FindBy(how = How.CLASS_NAME, using = "list-header-name")
    WebElement newList;

    //удаление списка
    @FindBy(how = How.CLASS_NAME, using = "list-header-extras-menu")
    WebElement operationWithListButton;

    @FindBy(how = How.CLASS_NAME, using = "js-close-list")
    WebElement archiveButton;

    //создать карточку

    @FindBy(how = How.CLASS_NAME, using = "js-add-a-card")
    WebElement createCardButton;

    @FindBy(how = How.CSS, using = "textarea[placeholder='Ввести заголовок для этой карточки']")
    WebElement inputNameOfCardtButton;

    @FindBy(how = How.CSS, using = "input[value='Добавить карточку']")
    WebElement addCardButton;

    //проверка созданной карточки
    @FindBy(how = How.CLASS_NAME, using = "list-card-title")
    WebElement newCard;

    //удалить карточку
    @FindBy(how = How.CSS, using = "span[class*=js-card-menu]")
    WebElement operationWithCarddButton;

    // список всех операций
    @FindBy(how = How.CLASS_NAME, using = "quick-card-editor-buttons-item-text")
    List<WebElement> allOperations;

    //перемещение карточки
    @FindBy(how = How.CSS, using = "div[class*=form-grid-child-threequarters]")
    WebElement choseNewList;

    @FindBy(how = How.CSS, using = "option[value='6060230532ebbd4a32819d47']")
    WebElement moveToNewList;

    @FindBy(how = How.CSS, using = "input[value='Переместить']")
    WebElement moveButton;

    @FindBy(how = How.CSS, using = "a[title='Перемещение']")
    WebElement moveButton1;

    @FindBy(how = How.CLASS_NAME, using = "js-sidebar-action-text")
    List<WebElement> allCardsButton;

    @FindBy(how = How.CSS, using = "option[value^='60']")
    List<WebElement> allPossibleListForMoving;

    @FindBy(how = How.CSS, using = "a[class*=js-close-window]")
    WebElement closeCardWindow;

    //все карточки
    @FindBy(how = How.CLASS_NAME, using = "list-card-title")
    public
    List<WebElement> allCards;

    @FindBy(how = How.CLASS_NAME, using = "list-card-detail")
    public
    List<WebElement> allCardsdetails;

    @FindBy(how = How.CLASS_NAME, using = "list-card-title")
    WebElement card;

    //выбор позиции карточки в списке
    @FindBy(how = How.XPATH, using = "//*[@id=\"chrome-container\"]/div[4]/div/div[2]/div/div/div/div/div[2]/div[2]")
    WebElement positionButton;

    @FindBy(how = How.CSS, using = "select.js-select-position option")
    List<WebElement> listOfOptions;
    // метка цвета
    @FindBy(how = How.CSS, using = "span[class*=mod-clickable]")
    WebElement colorLabel;

    public BoardPage(WebDriver driver) {
        super(driver);


    }

    public void renameBoard(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(renameBoard)).click();
        //wait.until(ExpectedConditions.elementToBeClickable(renameBoard)).sendKeys(Keys.BACK_SPACE);
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(renameBoard2)).sendKeys(name);
        //wait.until(ExpectedConditions.elementToBeClickable(renameBoard)).sendKeys(Keys.RETURN);

    }

    public void createList(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(createListButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(inputNameOfListButton)).sendKeys(name);
        wait.until(ExpectedConditions.elementToBeClickable(addListButton)).click();
    }

    public void createCard(String name) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(createCardButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(inputNameOfCardtButton)).sendKeys(name);
        wait.until(ExpectedConditions.elementToBeClickable(addCardButton)).click();
    }

    public WebElement selectOperationWithCard() {
        waitForElements(allOperations);
        List<WebElement> elementTeam = allOperations.stream().filter(element -> element.getText().equals("Архивировать")).collect(Collectors.toList());
        return elementTeam.get(0);
    }

    public WebElement selectOperationWithCard1() {
        waitForElements(allOperations);
        List<WebElement> elementTeam = allOperations.stream().filter(element -> element.getText().equals("Переместить")).collect(Collectors.toList());
        return elementTeam.get(0);
    }

    public void deleteList() {
        wait.until(ExpectedConditions.elementToBeClickable(operationWithListButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(archiveButton)).click();
    }

    public void deleteCard() throws InterruptedException {
        Thread.sleep(2000);
        // operationWithCarddButton.click();
        selectOperationWithCard().click();
        wait.until(ExpectedConditions.elementToBeClickable(addCardButton)).click();
    }

    public WebElement chooseCard(String name) throws InterruptedException {

        List<WebElement> elementTeam1 = allCards.stream().filter(element -> element.getText().equals(name)).collect(Collectors.toList());
        return elementTeam1.get(0);
        //wait.until(ExpectedConditions.elementToBeClickable(allCards(0))).click();
    }

    public WebElement chooseCardMovingButton() throws InterruptedException {

        List<WebElement> elementTeam1 = allCardsButton.stream().filter(element -> element.getText().equals("Перемещение")).collect(Collectors.toList());
        return elementTeam1.get(0);
    }


    public WebElement chooseOfOption(int n) throws InterruptedException {
        return listOfOptions.get(n);
    }

    public List<String> collectNamesOfCards() throws InterruptedException {
        Thread.sleep(2000);
        List<String> elementTeam1 = allCards.stream().map(element -> element.getAttribute("outerText")).sorted().collect(Collectors.toList());
        System.out.println(elementTeam1);
        return elementTeam1;
    }

    public List<String> collectNamesOfCardsWithoutSorting() throws InterruptedException {
        Thread.sleep(2000);
        List<String> elementTeam1 = allCards.stream().map(element -> element.getAttribute("outerText")).collect(Collectors.toList());
        System.out.println(elementTeam1);
        return elementTeam1;
    }
   /* public List <WebElement>sortedCards(List<String> b) throws InterruptedException {

    //    List<WebElement> elementTeam1 = allCards.stream().forEach(element -> if (element.getText().eq)).collect(Collectors.toList());
//     return elementTeam1;
  */

    public WebElement chooseListToMove(String name) throws InterruptedException {

        List<WebElement> elementTeam1 = allPossibleListForMoving.stream().filter(element -> element.getText().contains(name)).collect(Collectors.toList());
        return elementTeam1.get(0);
        //wait.until(ExpectedConditions.elementToBeClickable(allCards(0))).click();}
    }

    public void moveCard(String listName) throws InterruptedException {
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(card)).click();
        chooseCardMovingButton().click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(choseNewList)).click();
        chooseListToMove(listName).click();
        moveButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(closeCardWindow)).click();
    }

    public void moveColoredCard(String listName, int index) throws InterruptedException {
        Thread.sleep(2000);
        // wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element))).click();
        // wait.until(ExpectedConditions.visibilityOf(element)).click();
        allCards.get(index).click();
//        element.click();

        if (isCardWithColorOnPage() && (colorLabel.getAttribute("class").contains("card-label-red"))) {
            chooseCardMovingButton().click();
            Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(choseNewList)).click();
            chooseListToMove(listName).click();
            moveButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(closeCardWindow)).click();
        } else wait.until(ExpectedConditions.elementToBeClickable(closeCardWindow)).click();
    }


    public void sortedCard(int i, int c) throws InterruptedException {
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(allCards.get(c))).click();

        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(moveButton1)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(positionButton)).click();
        chooseOfOption(i).click();
        wait.until(ExpectedConditions.elementToBeClickable(moveButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(closeCardWindow)).click();
    }

    public boolean isListCreated(String name) {
        return newList.getText().equals(name);
    }

    public boolean isCardtCreated(String name) {
        return newCard.getText().contains(name);
    }

    public boolean isBoardRenamed(String name) {

        return renameBoard2.getAttribute("value").contains(name);
    }

    public boolean isCardWithColorOnPage() {

        return colorLabel.isDisplayed();
    }

}

