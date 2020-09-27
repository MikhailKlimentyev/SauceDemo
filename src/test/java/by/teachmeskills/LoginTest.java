package by.teachmeskills;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginTest {

    private WebDriver driver;

    @BeforeMethod
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/index.html");
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void verifyWhenSuccessfulLoginThenExpectedNumberOfProductsOnPageAndPrintNameAndPriceForProducts() {
        login("standard_user", "secret_sauce");

        List<WebElement> products = getProducts(By.className("inventory_item_label"));
        int expectedProductsNumber = 6;
        Assert.assertEquals(products.size(), expectedProductsNumber,
                String.format("Found products number is not equal %s", expectedProductsNumber));

        printNameAndPriceForProducts(products, By.className("inventory_item_name"),
                By.className("inventory_item_price"));
    }

    private void login(String login, String password) {
        WebElement loginInput = driver.findElement(By.id("user-name"));
        loginInput.sendKeys(login);
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys(password);
        WebElement loginButton = driver.findElement(By.className("btn_action"));
        loginButton.click();
    }

    private List<WebElement> getProducts(By locator) {
        return driver.findElements(locator);
    }

    private void printNameAndPriceForProducts(List<WebElement> products, By nameLocator, By priceLocator) {
        Map<String, String> productNamePriceMap = new HashMap<>();
        for (int index = 0; index < products.size(); index++) {
            String name = driver.findElements(nameLocator).get(index).getText();
            String price = driver.findElements(priceLocator).get(index).getText();
            productNamePriceMap.put(name, price);
        }
        for (Map.Entry entry : productNamePriceMap.entrySet()) {
            System.out.println(String.format("Name: %s, Price: %s", entry.getKey(), entry.getValue()));
        }
    }
}
