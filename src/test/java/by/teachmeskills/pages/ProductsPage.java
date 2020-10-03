package by.teachmeskills.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductsPage extends BasePage {

    public static final By PRODUCTS_LABEL_LOCATOR = By.className("product_label");
    public static final By PRODUCTS_ITEM_LOCATOR = By.className("inventory_item");
    public static final By PRODUCTS_ITEM_NAME_LOCATOR = By.className("inventory_item_name");
    public static final By PRODUCTS_ITEM_PRICE_LOCATOR = By.className("inventory_item_price");
    public static final By PRODUCTS_SORT_DROPDOWN_LOCATOR = By.cssSelector("select.product_sort_container");
    public static final By MENU_LOCATOR = By.xpath("//button[contains(text(),'Open Menu')]");

    public final String addToCartRemoveFromCartLocator = "//*[contains(text(),'%s')]/ancestor::" +
            "div[@class='inventory_item']//button";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void addToCartRemoveFromCart(String productName) {
        driver.findElement(By.xpath(String.format(addToCartRemoveFromCartLocator, productName))).click();
    }

    public String getProductsLabel() {
        return driver.findElement(PRODUCTS_LABEL_LOCATOR).getText();
    }

    public List<WebElement> getProductItems() {
        return driver.findElements(PRODUCTS_ITEM_LOCATOR);
    }

    public Map<String, String> getProductsNamePriceMap() {
        List<WebElement> items = driver.findElements(PRODUCTS_ITEM_LOCATOR);
        List<WebElement> nameElements = driver.findElements(PRODUCTS_ITEM_NAME_LOCATOR);
        List<WebElement> priceElements = driver.findElements(PRODUCTS_ITEM_PRICE_LOCATOR);
        Map<String, String> productsNamePriceMap = new LinkedHashMap<>();
        for (int index = 0; index < items.size(); index++) {
            String name = nameElements.get(index).getText();
            String price = priceElements.get(index).getText();
            productsNamePriceMap.put(name, price);
        }
        return productsNamePriceMap;
    }

    public void selectOptionInDropdown(String dropDownValue) {
        Select dropdown = new Select(driver.findElement(PRODUCTS_SORT_DROPDOWN_LOCATOR));
        dropdown.selectByValue(dropDownValue);
    }

    public String getAddToCartRemoveButtonName(String productName) {
        return driver.findElement(By.xpath(String.format(addToCartRemoveFromCartLocator, productName))).getText();
    }

    public MenuPage openMenu() {
        driver.findElement(MENU_LOCATOR).click();
        return new MenuPage(driver);
    }
}
