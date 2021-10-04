package com.slotegrator.steps.UI;

import com.slotegrator.config.DriverBase;
import com.slotegrator.pageObjects.DashboardElementsObjects;
import com.slotegrator.pageObjects.DashboardHomePage;
import com.slotegrator.pageObjects.LoginPage;
import com.slotegrator.pageObjects.PlayersPage;
import com.slotegrator.params.Constants;
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
import java.util.concurrent.TimeUnit;

public class PlayersPageSteps extends DriverBase {

    private final WebDriver driver = getDriver();
    private DashboardElementsObjects dashboardElementsObjects;
    private PlayersPage playersPage;

    public PlayersPageSteps() throws Exception {
    }

    @Given("I logged in at dashboard home page")
    public void iLoggedInAtDashboardHomePages() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.fillAdminCreds(Constants.ADMIN_LOGIN);
        loginPage.pressSignInButton();

        DashboardHomePage dashboardHomePage = new DashboardHomePage(driver);
        dashboardHomePage.waitForDashboardHomeElements();
        dashboardHomePage.checkDashboardURL();
        dashboardHomePage.checkForLoginUserArtifact(Constants.ADMIN_LOGIN);

    }

    @When("I click on the {string} tab in menus")
    public void iClickOnTheTabInMenus(String tabTitle) {
        dashboardElementsObjects = new DashboardElementsObjects(driver);
        dashboardElementsObjects.checkMenuIsExpanded();
        dashboardElementsObjects.clickMenuTab(tabTitle);
    }

    @Then("Sub-menu {string} is displayed")
    public void subMenuIsDisplayed(String tabTitle) {
        dashboardElementsObjects.checkSubMenuIsDisplayed(tabTitle);
    }

    @And("I click on the {string} sub-tab")
    public void iClickOnTheSubTab(String submenuTitle) {
        dashboardElementsObjects.clickSubMenuTab(submenuTitle);
    }

    @And("The table with the players is loaded")
    public void theTableWithThePlayersLoaded() {
        playersPage.checkPlayersTable();
    }

    @Given("I am at the players page")
    public void iAmAtThePlayersPage() {
        playersPage = new PlayersPage(driver);
        playersPage.navigateToPlayersPage();
        playersPage.checkPlayersPageArtifact();
        playersPage.checkPlayersTable();
        playersPage.checkPlayersURL();
        playersPage.waitForGridLoaded();

    }

    @When("I click on the column {string}")
    public void iClickOnTheColumn(String column) {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        playersPage.clickOnColumn(column);
        playersPage.waitForGridLoaded();

    }

    @Then("Data in column {string} will be ordered {string}")
    public void dataInColumnWillBeOrdered(String column, String type) {

        playersPage.getColumnIndex(column);
        playersPage.collectColumnData(playersPage.getColumnIndex());
        playersPage.checkSorting(type);

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