package by.teachmeskills.pages;

import by.teachmeskills.pages.base.BasePage;
import by.teachmeskills.utils.AllureUtils;
import io.qameta.allure.Step;
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
        String buttonName = driver.findElement(By.xpath(String.format(addRemoveButtonForProductName, productName)))
                .getText();
        if (buttonName.equals("ADD TO CART")) {
            addToCartRemoveFromCart(productName);
        } else {
            AllureUtils.takeScreenshot(driver);
            Assert.fail(String.format("There is no Add to cart button for %s product", productName));
        }
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Remove {productName} from cart")
    public ProductsPage removeFromCart(String productName) {
        String buttonName = driver.findElement(By.xpath(String.format(addRemoveButtonForProductName, productName)))
                .getText();
        if (buttonName.equals("REMOVE")) {
            addToCartRemoveFromCart(productName);
        } else {
            AllureUtils.takeScreenshot(driver);
            Assert.fail(String.format("There is no Remove button for %s product", productName));
        }
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    public ProductsPage addToCartRemoveFromCart(String productName) {
        driver.findElement(By.xpath(String.format(addRemoveButtonForProductName, productName))).click();
        return this;
    }

    @Step("Click on cart")
    public CartPage clickOnCart() {
        driver.findElement(SHOPPING_CART_ICON_LOCATOR).click();
        AllureUtils.takeScreenshot(driver);
        return new CartPage(driver);
    }

    public String getProductsNumberInShoppingCart() {
        return driver.findElement(SHOPPING_CART_LOCATOR).getAttribute("innerText");
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

    @Step("Select dropdown option {dropDownValue}")
    public ProductsPage selectOptionInDropdown(String dropDownValue) {
        Select dropdown = new Select(driver.findElement(PRODUCTS_SORT_DROPDOWN_LOCATOR));
        dropdown.selectByValue(dropDownValue);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    public String getAddToCartRemoveButtonName(String productName) {
        return driver.findElement(By.xpath(String.format(addRemoveButtonForProductName, productName))).getText();
    }

    @Step("Open menu")
    public ModalMenuPage openMenu() {
        driver.findElement(MENU_LOCATOR).click();
        AllureUtils.takeScreenshot(driver);
        return new ModalMenuPage(driver);
    }

    public ProductsPage areNamesVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCTS_ITEM_NAME_LOCATOR));
        } catch (TimeoutException ex) {
            Assert.fail("Names are not opened");
        } finally {
            return this;
        }
    }

    public ProductsPage arePricesVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCTS_ITEM_PRICE_LOCATOR));
        } catch (TimeoutException ex) {
            Assert.fail("Prices are not visible");
        } finally {
            return this;
        }
    }

    public ProductsPage isRemoveButtonVisible(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
                    String.format(removeButtonForProductName, productName))));
        } catch (TimeoutException ex) {
            Assert.fail("Remove button is not visible");
        } finally {
            return this;
        }
    }

    public ProductsPage isAddButtonVisible(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(
                    String.format(addButtonForProductName, productName))));
        } catch (TimeoutException ex) {
            Assert.fail("Add button is not visible");
        } finally {
            return this;
        }
    }

    @Step("Open products page")
    @Override
    public ProductsPage openPage() {
        driver.get(PRODUCTS_PAGE_URL);
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Override
    public ProductsPage isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTS_LABEL_LOCATOR));
        } catch (TimeoutException ex) {
            Assert.fail("Products Page is not opened");
        } finally {
            return this;
        }
    }
}
