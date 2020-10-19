package by.teachmeskills.tests;

import by.teachmeskills.pages.CartPage;
import by.teachmeskills.pages.LoginPage;
import by.teachmeskills.pages.ProductsPage;
import by.teachmeskills.steps.CartSteps;
import by.teachmeskills.steps.FinishSteps;
import by.teachmeskills.steps.LoginSteps;
import by.teachmeskills.steps.ProductsSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import static by.teachmeskills.domain.Constants.PASSWORD;
import static by.teachmeskills.domain.Constants.STANDARD_USER_USER_NAME;


public class BaseTest {

    protected WebDriver driver;

    protected LoginPage loginPage;
    protected CartPage cartPage;
    protected CartSteps cartSteps;
    protected ProductsSteps productsSteps;
    protected LoginSteps loginSteps;
    protected FinishSteps finishSteps;

    @BeforeMethod
    public void setUp() {
        if (System.getProperty("browser", "chrome").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        createInstances();
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    public ProductsPage safelyLogin() {
        return loginPage
                .openPage()
                .isPageOpened()
                .login(STANDARD_USER_USER_NAME, PASSWORD)
                .isPageOpened();
    }

    private void createInstances() {
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        cartSteps = new CartSteps();
        productsSteps = new ProductsSteps();
        loginSteps = new LoginSteps();
        finishSteps = new FinishSteps();
    }
}
