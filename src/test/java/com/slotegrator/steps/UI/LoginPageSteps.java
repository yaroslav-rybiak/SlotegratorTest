package com.slotegrator.steps.UI;

import com.slotegrator.config.DriverBase;
import com.slotegrator.pageObjects.DashboardHomePage;
import com.slotegrator.pageObjects.LoginPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class LoginPageSteps extends DriverBase {

    private final WebDriver driver = getDriver();
    private LoginPage loginPage;
    private DashboardHomePage dashboardHomePage;
    public LoginPageSteps() throws Exception {
    }

    @Given("I am at the login page")
    public void iAmAtTheLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
    }

    @When("I log in as {string} user")
    public void iLoggingInAs(String login) {
        loginPage.fillAdminCreds(login);
    }

    @And("I click the Sign in button")
    public void iClickTheSignInButton() {
        loginPage.pressSignInButton();
    }

    @Then("I should be at the dashboard home page")
    public void iShouldBeAtTheDashboardHomePage() {
        dashboardHomePage = new DashboardHomePage(driver);
        dashboardHomePage.waitForDashboardHomeElements();
        dashboardHomePage.checkDashboardURL();
    }

    @And("I should be logged as {string} user")
    public void iShouldBeLoggedAsUser(String login) {
        dashboardHomePage.checkForLoginUserArtifact(login);
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/screenshots/" + scenario.getName() + " " + System.nanoTime() + ".png"));
            System.out.println("the Screenshot is taken");
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
