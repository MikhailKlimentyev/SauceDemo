package by.teachmeskills.pages;

import by.teachmeskills.pages.base.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static by.teachmeskills.domain.Constants.BASE_URL;

@Log4j2
public class CartPage extends BasePage {

    public static final String CART_PAGE_URI = "/cart.html";
    public static final String CART_PAGE_URL = BASE_URL + CART_PAGE_URI;

    public static final By CHECKOUT_BUTTON_LOCATOR = By.cssSelector(".checkout_button");
    public static final By CONTINUE_SHOPPING_LOCATOR = By.xpath("//*[contains(text(),'Continue Shopping')]");
    public static final By PRODUCT_IN_SHOPPING_CART_LOCATOR = By.cssSelector(".cart_item");

    public String pricePattern = "//*[contains(text(),'%s')]/ancestor::*[@class='cart_item']" +
            "//div[@class='inventory_item_price']";
    public String quantityPattern = "//*[contains(text(),'%s')]/ancestor::*[@class='cart_item']" +
            "//div[@class='cart_quantity']";
    public String removeButtonPattern = "//*[contains(text(), '%s')]/../following-sibling::*" +
            "//button[contains(@class, 'cart_button')]";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductPrice(String productName) {
        By locator = By.xpath(String.format(pricePattern, productName));
        log.debug(String.format("Getting product price for product name %s with locator %s", productName, locator));
        String productPrice = driver.findElement(locator).getText();
        log.debug(String.format("Product price is %s", productPrice));
        return productPrice;
    }

    public String getProductQuantity(String productName) {
        By locator = By.xpath(String.format(quantityPattern, productName));
        log.debug(String.format("Getting product quantity for product name %s with locator %s", productName, locator));
        String productQuantity = driver.findElement(locator).getText();
        log.debug(String.format("Product quantity is %s", productQuantity));
        return productQuantity;
    }

    @Step("Click on check out button")
    public CheckoutInformationPage clickOnCheckOutButton() {
        log.info(String.format("Clicking on checkout button with locator %s", CHECKOUT_BUTTON_LOCATOR));
        driver.findElement(CHECKOUT_BUTTON_LOCATOR).click();
        return new CheckoutInformationPage(driver);
    }

    @Step("Click on continue shopping button")
    public ProductsPage clickOnContinueShoppingButton() {
        log.info(String.format("Clicking on continue shopping button with locator %s", CONTINUE_SHOPPING_LOCATOR));
        driver.findElement(CONTINUE_SHOPPING_LOCATOR).click();
        return new ProductsPage(driver);
    }

    @Step("Click on remove button for {productName}")
    public CartPage clickOnRemoveButton(String productName) {
        By locator = By.xpath(String.format(removeButtonPattern, productName));
        log.info(String.format("Clicking on remove button for %s with locator %s", productName, locator));
        driver.findElement(locator).click();
        return this;
    }

    public int getProductsNumberInShoppingCart() {
        log.debug(String.format("Getting products number in shopping cart with locator %s",
                PRODUCT_IN_SHOPPING_CART_LOCATOR));
        int productsNumber = driver.findElements(PRODUCT_IN_SHOPPING_CART_LOCATOR).size();
        log.debug(String.format("Products number in shopping cart is %s", productsNumber));
        return productsNumber;
    }

    @Step("Open cart page")
    @Override
    public CartPage openPage() {
        log.debug(String.format("Opening cart page with %s url", CART_PAGE_URL));
        driver.get(CART_PAGE_URL);
        return this;
    }

    @Override
    public CartPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_BUTTON_LOCATOR));
        } catch (TimeoutException ex) {
            log.error(String.format("Cart page is not opened. Element with locator %s is not visible on the page",
                    CHECKOUT_BUTTON_LOCATOR));
            Assert.fail("Cart page is not opened");
        } finally {
            return this;
        }
    }
}
