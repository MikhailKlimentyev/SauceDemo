package by.teachmeskills.pages;

import by.teachmeskills.pages.base.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static by.teachmeskills.domain.Constants.BASE_URL;

@Log4j2
public class ProductsPage extends BasePage {

    public static final String PRODUCTS_PAGE_URI = "/inventory.html";
    public static final String PRODUCTS_PAGE_URL = BASE_URL + PRODUCTS_PAGE_URI;

    public static final By PRODUCTS_LABEL_LOCATOR = By.className("product_label");
    public static final By PRODUCTS_ITEM_LOCATOR = By.className("inventory_item");
    public static final By PRODUCTS_ITEM_NAME_LOCATOR = By.className("inventory_item_name");
    public static final By PRODUCTS_ITEM_PRICE_LOCATOR = By.className("inventory_item_price");
    public static final By PRODUCTS_SORT_DROPDOWN_LOCATOR = By.cssSelector("select.product_sort_container");
    public static final By MENU_LOCATOR = By.xpath("//button[contains(text(),'Open Menu')]");
    public static final By SHOPPING_CART_LOCATOR = By.cssSelector("#shopping_cart_container");
    public static final By SHOPPING_CART_ICON_LOCATOR = By.cssSelector(".shopping_cart_link");

    public final String commonPathForAddRemoveButton = "//*[contains(text(),'%s')]/ancestor::" +
            "*[@class='inventory_item']";
    public final String addRemoveButtonForProductName = commonPathForAddRemoveButton
            + "//button";
    public final String removeButtonForProductName = commonPathForAddRemoveButton
            + "//button[contains(@class,'tn_secondary')]";
    public final String addButtonForProductName = commonPathForAddRemoveButton
            + "//button[contains(@class,'btn_primary')]";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Add {productName} to cart")
    public ProductsPage addToCart(String productName) {
        return addToCartRemoveFromCart(productName, "ADD TO CART");
    }

    @Step("Remove {productName} from cart")
    public ProductsPage removeFromCart(String productName) {
        return addToCartRemoveFromCart(productName, "REMOVE");
    }

    public ProductsPage addToCartRemoveFromCart(String productName) {
        By buttonLocator = By.xpath(String.format(addRemoveButtonForProductName, productName));
        log.info(String.format("Click on button %s for product %s with locator %s",
                driver.findElement(buttonLocator).getText(), productName, buttonLocator));
        driver.findElement(buttonLocator).click();
        return this;
    }

    @Step("Click on cart")
    public CartPage clickOnCart() {
        log.info(String.format("Click on cart with locator %s", SHOPPING_CART_ICON_LOCATOR));
        driver.findElement(SHOPPING_CART_ICON_LOCATOR).click();
        return new CartPage(driver);
    }

    public String getProductsNumberInShoppingCart() {
        return driver.findElement(SHOPPING_CART_LOCATOR).getAttribute("innerText");
    }

    public String getProductsLabel() {
        log.debug(String.format("Getting products label with locator %s", PRODUCTS_LABEL_LOCATOR));
        String productsLabel = driver.findElement(PRODUCTS_LABEL_LOCATOR).getText();
        log.debug(String.format("Products label is %s", productsLabel));
        return productsLabel;
    }

    public List<WebElement> getProductItems() {
        log.debug(String.format("Getting product items with locator %s", PRODUCTS_ITEM_LOCATOR));
        List<WebElement> elements = driver.findElements(PRODUCTS_ITEM_LOCATOR);
        log.debug(String.format("Product items size is %s", elements.size()));
        return elements;
    }

    public Map<String, String> getProductsNamePriceMap() {
        log.debug(String.format("Getting items with locator %s", PRODUCTS_ITEM_LOCATOR));
        List<WebElement> items = driver.findElements(PRODUCTS_ITEM_LOCATOR);
        log.debug(String.format("Items size is %s", items.size()));
        log.debug(String.format("Getting name elements with locator %s", PRODUCTS_ITEM_NAME_LOCATOR));
        List<WebElement> nameElements = driver.findElements(PRODUCTS_ITEM_NAME_LOCATOR);
        log.debug(String.format("Name elements size is %s", nameElements.size()));
        log.debug(String.format("Getting price elements with locator %s", PRODUCTS_ITEM_PRICE_LOCATOR));
        List<WebElement> priceElements = driver.findElements(PRODUCTS_ITEM_PRICE_LOCATOR);
        log.debug(String.format("Price elements size is %s", priceElements.size()));
        Map<String, String> productsNamePriceMap = new LinkedHashMap<>();
        for (int index = 0; index < items.size(); index++) {
            String name = nameElements.get(index).getText();
            String price = priceElements.get(index).getText();
            productsNamePriceMap.put(name, price);
        }
        log.debug(String.format("products name price map size is %s", productsNamePriceMap.size()));
        return productsNamePriceMap;
    }

    @Step("Select dropdown option {dropDownValue}")
    public ProductsPage selectOptionInDropdown(String dropDownValue) {
        log.info(String.format("Selecting value in %s in products sort dropdown with locator %s",
                dropDownValue, PRODUCTS_SORT_DROPDOWN_LOCATOR));
        Select dropdown = new Select(driver.findElement(PRODUCTS_SORT_DROPDOWN_LOCATOR));
        dropdown.selectByValue(dropDownValue);
        return this;
    }

    public String getAddToCartRemoveButtonName(String productName) {
        By locator = By.xpath(String.format(addRemoveButtonForProductName, productName));
        log.debug(String.format("Getting button with locator %s", locator));
        String text = driver.findElement(locator).getText();
        log.debug(String.format("Button name is %s", text));
        return text;
    }

    @Step("Open menu")
    public ModalMenuPage openMenu() {
        log.info(String.format("Click on menu with locator %s", MENU_LOCATOR));
        driver.findElement(MENU_LOCATOR).click();
        return new ModalMenuPage(driver);
    }

    public ProductsPage areNamesVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCTS_ITEM_NAME_LOCATOR));
        } catch (TimeoutException ex) {
            log.error(String.format("Element with locator %s is not visible on the page", PRODUCTS_ITEM_NAME_LOCATOR));
            Assert.fail("Names are not visible");
        } finally {
            return this;
        }
    }

    public ProductsPage arePricesVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCTS_ITEM_PRICE_LOCATOR));
        } catch (TimeoutException ex) {
            log.error(String.format("Element with locator %s is not visible on the page", PRODUCTS_ITEM_PRICE_LOCATOR));
            Assert.fail("Prices are not visible");
        } finally {
            return this;
        }
    }

    public ProductsPage isRemoveButtonVisible(String productName) {
        By locator = By.xpath(String.format(removeButtonForProductName, productName));
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (TimeoutException ex) {
            log.error(String.format("Remove button with locator %s is not visible on the page", locator));
            Assert.fail("Remove button is not visible");
        } finally {
            return this;
        }
    }

    public ProductsPage isAddButtonVisible(String productName) {
        By locator = By.xpath(String.format(addButtonForProductName, productName));
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (TimeoutException ex) {
            log.error(String.format("Add button with locator %s is not visible on the page", locator));
            Assert.fail("Add button is not visible");
        } finally {
            return this;
        }
    }

    @Step("Open products page")
    @Override
    public ProductsPage openPage() {
        log.debug(String.format("Opening products page with %s url", PRODUCTS_PAGE_URL));
        driver.get(PRODUCTS_PAGE_URL);
        return this;
    }

    @Override
    public ProductsPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTS_LABEL_LOCATOR));
        } catch (TimeoutException ex) {
            log.error(String.format("Products page is not opened. " +
                    "Element with locator %s is not visible on the page", PRODUCTS_LABEL_LOCATOR));
            Assert.fail("Products Page is not opened");
        } finally {
            return this;
        }
    }

    private ProductsPage addToCartRemoveFromCart(String productName, String buttonName) {
        By buttonLocator = By.xpath(String.format(addRemoveButtonForProductName, productName));
        log.debug(String.format("Getting button name for element with locator %s and product name %s",
                buttonLocator, productName));
        String buttonNameText = driver.findElement(buttonLocator).getText();
        log.debug(String.format("Button name is %s", buttonName));
        if (buttonNameText.equals(buttonName)) {
            addToCartRemoveFromCart(productName);
        } else {
            String message = String.format("There is no %s button for %s product", buttonName, productName);
            log.error(message);
            Assert.fail(message);
        }
        return this;
    }
}
