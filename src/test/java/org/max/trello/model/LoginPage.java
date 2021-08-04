package org.max.trello.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class LoginPage extends BasePage {




    @FindBy(how = How.CLASS_NAME, using = "label")
    WebElement googleButton;

    @FindBy(how = How.CLASS_NAME, using = "css-t5emrf")
    List<WebElement> comeInButton;

    @FindBy(how = How.CSS, using = "input[value='Войти с помощью Atlassian']")
    WebElement comeInAttlasianButton;

    @FindBy(how = How.NAME, using = "identifier")
    WebElement googleAdress;

    @FindBy(how = How.CSS, using = "input[placeholder='Укажите адрес электронной почты']")
    WebElement adress;

    @FindBy(how = How.NAME, using = "password")
    WebElement googlePaasword;

    @FindBy(how = How.CSS, using = "input[placeholder='Введите пароль']")
    WebElement password;

    @FindBy(how = How.CLASS_NAME, using = "VfPpkd-RLmnJb")
    WebElement nextButton;

    @FindBy(how = How.ID, using = "login")
    WebElement chekingButton;

    //кнопка аккаунта

    @FindBy(how = How.CLASS_NAME, using = "_24AWINHReYjNBf")
    WebElement accountButton;

    @FindBy(how = How.CLASS_NAME, using = "_2TvKKP0vwCN5Zd")
    WebElement email;



    public LoginPage(WebDriver driver) {
      super(driver);
    }
    public void accountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(accountButton)).click();}

    public WebElement comeInButton() {
        List<WebElement> elementTeam1 = comeInButton.stream().filter(element -> element.getText().equals("Войти")).collect(Collectors.toList());
        return elementTeam1.get(0);}

    public void comeInAtlasianButton() {
        wait.until(ExpectedConditions.elementToBeClickable(comeInAttlasianButton)).click();}


    public String getEmail() {

        return email.getText();}

    public void clickGoogleButton() {
        wait.until(ExpectedConditions.elementToBeClickable(googleButton)).click();
    }

    public void sendGoogleAdress(String adress) {
        wait.until(ExpectedConditions.elementToBeClickable(googleAdress)).sendKeys(adress);
    }

    public void sendAdress(String adress_) {
        wait.until(ExpectedConditions.elementToBeClickable(adress)).sendKeys(adress_);
    }

    public void clicknextButton() {
        nextButton.click();
    }

    public void sendGooglePassword() {
        wait.until(ExpectedConditions.elementToBeClickable(googlePaasword)).sendKeys("kingkong88");
    }

    public void sendPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys("kingkong88");
    }

    public boolean isOnPage() {
        return chekingButton!=null && chekingButton.getText().equals("Войти");
    }
}

