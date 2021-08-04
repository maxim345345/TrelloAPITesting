package org.max.trello.model;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;

public class StartPage extends BasePage {


    private String PAGE_URL;

    @FindBy(how = How.LINK_TEXT, using = "Войти")
    WebElement signinButton;


    public StartPage(String url, WebDriver driver) {
        super(driver);
        this.PAGE_URL = url;
        driver.get(PAGE_URL);


    }

    public void clickSignInButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signinButton)).click();
    }

}
