package org.max.trello.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class MainMenu extends BasePage {

    @FindBy(how = How.CLASS_NAME, using = "_18BZzkmkt4UatG")
    WebElement finaldesk;



    //кнопка Доски в ГМ
    @FindBy(how = How.CLASS_NAME, using = "MEu8ZECLGMLeab")
    WebElement allBords;

    //создание новой доски
    @FindBy(how = How.CLASS_NAME, using = "_2Z9q8nPvS1HJuT")
    WebElement findByName;

    @FindBy(how = How.CLASS_NAME, using = "bHF9FML4QxCHFM")
    WebElement makeNewBoard;

    @FindBy(how = How.CLASS_NAME, using = "uj9Ovoj4USRUQz")
    WebElement makeBoardFinal;

    @FindBy(how = How.CSS, using = "a[title='Закрыть меню доски.']")
    WebElement closeMenuOfBoard;

    //кнопка домой
    @FindBy(how = How.CSS, using = "span[aria-label='HouseIcon']")
    WebElement home;

    // кнопка перехода в окно team
    @FindBy(how = How.CSS, using = "span[class*=_2cXFTH_8wo4pyK]")
    WebElement goToTeam;

    //закрытие досок
    @FindBy(how = How.LINK_TEXT, using = "Меню")
    WebElement menuButton;

    @FindBy(how = How.CSS, using = "a[href='https://trello.com/team69621806']")
    WebElement desk;

    @FindBy(how = How.LINK_TEXT, using = "Ещё")
    WebElement otherButton;

    @FindBy(how = How.LINK_TEXT, using = "Закрыть доску…")
    WebElement closeBoardButton;

    @FindBy(how = How.CSS, using = "input[value='Закрыть']")
    WebElement closeButton;

    // список всех бордов
    @FindBy(how = How.CLASS_NAME, using = "boards-page-board-section-list-item")
    List<WebElement> boards;


    //выбор меню в team
    @FindBy(how = How.CSS, using = "span[data-test-id='home-team-tab-name']")
    List<WebElement> teamList;

    //клик по кнопке доски в team
    @FindBy(how = How.CLASS_NAME, using = "_3qwe2tMMFonNvf")
    List<WebElement> teamBoards;

    @FindBy(how = How.CLASS_NAME, using = "list-card")
    List<WebElement> listOfCards;

    //Борды по названию
    @FindBy(how = How.CLASS_NAME, using = "board-tile-details-name")
    List<WebElement> boardsByNames;

    public MainMenu(WebDriver driver) {
        super(driver);
    }



    public void createNewBoard(String name) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(allBords)).click();
        wait.until(ExpectedConditions.elementToBeClickable(findByName)).sendKeys(name);
        wait.until(ExpectedConditions.elementToBeClickable(makeNewBoard)).click();
        wait.until(ExpectedConditions.elementToBeClickable(makeBoardFinal)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(closeMenuOfBoard)).click();
        wait.until(ExpectedConditions.elementToBeClickable(home)).click();
    }


    public boolean isPageOpened(){
        return finaldesk.getText().toString().contains("Эта команда исчерпала лимит открытых досок.");}

    public void openBoard(String name) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(allBords)).click();
        wait.until(ExpectedConditions.elementToBeClickable(findByName)).sendKeys(name);
        wait.until(ExpectedConditions.elementToBeClickable(findByName)).sendKeys(Keys.RETURN);
    }


    public WebElement selectTeam(String someTeam) {
        waitForElements(teamList); //TODO YA TUT!!!!
        List<WebElement> elementTeam = teamList.stream().filter(element -> element.getText().equals(someTeam)).collect(Collectors.toList());
        return elementTeam.get(0);
    }

    public void openTeamBoards(String someTeam) {
        WebElement selectedTeam = selectTeam(someTeam);
        selectedTeam.findElements(By.tagName("ul"));
    }

    public List<WebElement> teamBoards() {
        List<WebElement> elementTeam1 = teamBoards.stream().filter(element -> element.getText().equals("Доски")).collect(Collectors.toList());
        return elementTeam1;
    }

    public WebElement boardsByname(String title) {
        List<WebElement> elementTeam1 = boardsByNames.stream().filter(element -> element.getText().equals(title)).collect(Collectors.toList());
        return elementTeam1.get(0);
    }

    public void deleteDesk(WebElement desk) throws InterruptedException {
        Thread.sleep(4000);
        desk.click();
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(otherButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(closeBoardButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(home)).click();
    }

    public void deleteNewBoard() throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(otherButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(closeBoardButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(closeButton)).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(home)).click();
    }

    public boolean isBoardInMainMenu(String name) {
List<WebElement> board=boardsByNames.stream().filter(element -> element.getText().equals(name)).collect(Collectors.toList());
        return board.get(0).getText().contains(name);
    }
}
