package by.teachmeskills.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static by.teachmeskills.domain.Constants.*;

public class ProductsTest extends BaseTest {

    @Test
    public void productShouldBeAddedIntoCart() {
        loginPage.openPage();
        loginPage.login(STANDARD_USER_USER_NAME, PASSWORD);
        productsPage.addToCartRemoveFromCart(SAUCE_LABS_FLEECE_JACKET_NAME);
        cartPage.openPage();

        String actualPrice = cartPage.getProductPrice(SAUCE_LABS_FLEECE_JACKET_NAME);
        String actualQuantity = cartPage.getProductQuantity(SAUCE_LABS_FLEECE_JACKET_NAME);
        cartSteps.productDetailsShouldBeLike(actualPrice, actualQuantity, SAUCE_LABS_FLEECE_JACKET_PRICE.substring(1),
                "1");
    }

    @Test(dataProvider = "dropdownValuesSortingParametersDataProvider")
    public void productsShouldBeSortedByNameAndByPriceInDirectAndReverseOrder(String dropdownValue,
                                                                              String sortingParameter,
                                                                              boolean sortingOrder) {
        loginPage.openPage();
        loginPage.login(STANDARD_USER_USER_NAME, PASSWORD);
        productsPage.selectOptionInDropdown(dropdownValue);
        Map<String, String> productsNamePriceMap = productsPage.getProductsNamePriceMap();
        Map<String, String> expectedProductsNamePriceMap = productsSteps.sortProducts(PRODUCT_NAME_PRICE_MAP,
                sortingParameter, sortingOrder);
        productsSteps.productNamesAndPricesShouldBeLike(productsNamePriceMap, expectedProductsNamePriceMap);
    }

    @DataProvider(name = "dropdownValuesSortingParametersDataProvider")
    public static Object[][] dropdownValuesSortingParametersDataProvider() {
        return new Object[][]{
                {AZ_DROPDOWN_VALUE, NAME_SORTING_PARAMETER, true},
                {ZA_DROPDOWN_VALUE, NAME_SORTING_PARAMETER, false},
                {LOHI_DROPDOWN_VALUE, PRICE_SORTING_PARAMETER, true},
                {HILO_DROPDOWN_VALUE, PRICE_SORTING_PARAMETER, false},
        };
    }

    @Test
    public void buttonToAddRemoveProductShouldChangeNameAfterAddingRemovingProduct() {
        loginPage.openPage();
        loginPage.login(STANDARD_USER_USER_NAME, PASSWORD);
        productsPage.addToCartRemoveFromCart(SAUCE_LABS_FLEECE_JACKET_NAME);
        String removeButtonName = productsPage.getAddToCartRemoveButtonName(SAUCE_LABS_FLEECE_JACKET_NAME);
        productsSteps.buttonNameShouldBeLike(removeButtonName, REMOVE_BUTTON_NAME);

        productsPage.addToCartRemoveFromCart(SAUCE_LABS_FLEECE_JACKET_NAME);
        String addToCartButtonName = productsPage.getAddToCartRemoveButtonName(SAUCE_LABS_FLEECE_JACKET_NAME);
        productsSteps.buttonNameShouldBeLike(addToCartButtonName, ADD_TO_CART_BUTTON_NAME);
    }
}
