package org.max.bm_parts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private String PAGE_URL;

    public LoginPage(String url, WebDriver driver) {
        super(driver);
        this.PAGE_URL = url;
        driver.get(PAGE_URL);
    }
    @FindBy(how = How.CSS, using = ".mat-menu-trigger.button-cart-selected")
    WebElement busket;

    @FindBy(how = How.CSS, using = "input[placeholder='Введите логин']")
    WebElement login;

    @FindBy(how = How.CSS, using = "input[placeholder='Введите пароль']")
    WebElement password;

    @FindBy(how = How.CSS, using = "button[type='submit']")
    WebElement enterButton;

    public void sendLogin(String log) throws InterruptedException {
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(login)).sendKeys(log);
    }

    public void sendPassword(String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(password)).sendKeys(pass);
    }

    public void enterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(enterButton)).click();
    }

    public boolean isOnPage() {
        return busket!=null && busket.getAttribute("class").contains("bbutton-cart-selected");
    }
}


