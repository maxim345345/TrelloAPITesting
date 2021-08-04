package org.max.trello.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, 0);

        PageFactory.initElements(driver, this);

    }


    protected void waitForElements(List<WebElement> element) throws Error {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }
    protected void waitForElement(WebElement element) throws Error {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }


}


