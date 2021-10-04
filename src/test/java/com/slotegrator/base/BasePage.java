package com.slotegrator.base;

import com.slotegrator.pageObjects.LoginPage;
import com.slotegrator.pageObjects.PlayersPage;
import com.slotegrator.params.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class BasePage {

    protected WebDriver driver;
    protected String password = Constants.ADMIN_PASSWORD;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage navigateToLoginPage() {
        driver.navigate().to(Constants.LOGIN_URL);
        assertEquals("Dashboard - Casino", driver.getTitle());
        By signInFormSelector = By.xpath("//div[@class=\"signin-body\"]//form[@action=\"/admin/login\"]");

        WebElement signInForm = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(signInFormSelector));

        return new LoginPage(driver);
    }

    public PlayersPage navigateToPlayersPage() {
        driver.navigate().to(Constants.PLAYERS_URL);
        assertEquals("Dashboard - Player management", driver.getTitle());

        return new PlayersPage(driver);
    }
}