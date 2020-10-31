package by.teachmeskills.tests;

import by.teachmeskills.assertions.CartAssert;
import by.teachmeskills.assertions.FinishAssert;
import by.teachmeskills.assertions.LoginAssert;
import by.teachmeskills.assertions.ProductsAssert;
import by.teachmeskills.pages.CartPage;
import by.teachmeskills.pages.LoginPage;
import by.teachmeskills.pages.ProductsPage;
import by.teachmeskills.tests.listeners.TestListener;
import by.teachmeskills.utils.CapabilitiesGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.concurrent.TimeUnit;

import static by.teachmeskills.domain.Constants.PASSWORD;
import static by.teachmeskills.domain.Constants.STANDARD_USER_USER_NAME;

@Listeners(TestListener.class)
public class BaseTest {

    protected WebDriver driver;

    protected LoginPage loginPage;
    protected CartPage cartPage;
    protected CartAssert cartAssert;
    protected ProductsAssert productsAssert;
    protected LoginAssert loginAssert;
    protected FinishAssert finishAssert;

    @BeforeMethod
    public void setUp(ITestContext context) {
        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        setDriverAttribute(context);
        createInstances();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public ProductsPage loginSafely() {
        return loginPage
                .openPage()
                .isPageOpened()
                .loginSafely(STANDARD_USER_USER_NAME, PASSWORD)
                .isPageOpened();
    }

    private void createInstances() {
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        cartAssert = new CartAssert();
        productsAssert = new ProductsAssert();
        loginAssert = new LoginAssert();
        finishAssert = new FinishAssert();
    }

    private void setDriverAttribute(ITestContext context) {
        String variable = "driver";
        System.out.println("Setting driver into context with variable name " + variable);
        context.setAttribute(variable, driver);
    }
}
