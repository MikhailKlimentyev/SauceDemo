package by.teachmeskills.tests;

import by.teachmeskills.pages.*;
import by.teachmeskills.steps.CartSteps;
import by.teachmeskills.steps.FinishSteps;
import by.teachmeskills.steps.LoginSteps;
import by.teachmeskills.steps.ProductsSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected WebDriver driver;

    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected CheckoutInformationPage checkoutInformationPage;
    protected CheckoutOverviewPage checkoutOverviewPage;
    protected FinishPage finishPage;
    protected CartSteps cartSteps;
    protected ProductsSteps productsSteps;
    protected LoginSteps loginSteps;
    protected FinishSteps finishSteps;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        createInstances();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    private void createInstances() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutInformationPage = new CheckoutInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        finishPage = new FinishPage(driver);
        cartSteps = new CartSteps();
        productsSteps = new ProductsSteps();
        loginSteps = new LoginSteps();
        finishSteps = new FinishSteps();
    }
}
