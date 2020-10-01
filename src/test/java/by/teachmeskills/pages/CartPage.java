package by.teachmeskills.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static by.teachmeskills.domain.Constants.BASE_URL;

public class CartPage extends BasePage {

    public static final String CART_PAGE_URI = "/cart.html";
    public static final String CART_PAGE_URL = BASE_URL + CART_PAGE_URI;

    public static final By CHECKOUT_BUTTON = By.cssSelector(".checkout_button");

    public String priceLocator = "//*[contains(text(),'%s')]/ancestor::*[@class='cart_item']" +
            "//div[@class='inventory_item_price']";
    public String quantityLocator = "//*[contains(text(),'%s')]/ancestor::*[@class='cart_item']" +
            "//div[@class='cart_quantity']";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(CART_PAGE_URL);
    }

    public String getProductPrice(String productName) {
        return driver.findElement(By.xpath(String.format(priceLocator, productName))).getText();
    }

    public String getProductQuantity(String productName) {
        return driver.findElement(By.xpath(String.format(quantityLocator, productName))).getText();
    }

    public void clickOnCheckOutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }
}
