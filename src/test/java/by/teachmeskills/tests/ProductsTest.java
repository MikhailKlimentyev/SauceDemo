package by.teachmeskills.tests;

import by.teachmeskills.pages.CartPage;
import by.teachmeskills.pages.ProductsPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

import static by.teachmeskills.domain.Constants.*;

public class ProductsTest extends BaseTest {

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
    public void productShouldBeAddedIntoCart() {
        ProductsPage productsPage = safelyLogin();
        productsPage
                .addToCartRemoveFromCart(SAUCE_LABS_FLEECE_JACKET_NAME);
        cartPage
                .openPage()
                .isPageOpened();

        String actualPrice = cartPage.getProductPrice(SAUCE_LABS_FLEECE_JACKET_NAME);
        String actualQuantity = cartPage.getProductQuantity(SAUCE_LABS_FLEECE_JACKET_NAME);
        cartSteps.productDetailsShouldBeLike(actualPrice, actualQuantity, SAUCE_LABS_FLEECE_JACKET_PRICE.substring(1),
                "1");
    }

    @Test(dataProvider = "dropdownValuesSortingParametersDataProvider")
    public void productsShouldBeSortedByNameAndByPriceInDirectAndReverseOrder(String dropdownValue,
                                                                              String sortingParameter,
                                                                              boolean sortingOrder) {
        ProductsPage productsPage = safelyLogin();
        Map<String, String> productsNamePriceMap = productsPage
                .selectOptionInDropdown(dropdownValue)
                .getProductsNamePriceMap();

        Map<String, String> expectedProductsNamePriceMap = productsSteps.sortProducts(PRODUCT_NAME_PRICE_MAP,
                sortingParameter, sortingOrder);
        productsPage.areNamesVisible();
        productsPage.arePricesVisible();
        productsSteps.productNamesAndPricesShouldBeLike(productsNamePriceMap, expectedProductsNamePriceMap);
    }

    @Test
    public void buttonToAddRemoveProductShouldChangeNameAfterAddingRemovingProduct() {
        ProductsPage productsPage = safelyLogin();
        String removeButtonName = productsPage
                .addToCartRemoveFromCart(SAUCE_LABS_FLEECE_JACKET_NAME)
                .isRemoveButtonVisible(SAUCE_LABS_FLEECE_JACKET_NAME)
                .getAddToCartRemoveButtonName(SAUCE_LABS_FLEECE_JACKET_NAME);
        productsSteps.buttonNameShouldBeLike(removeButtonName, REMOVE_BUTTON_NAME);

        String addToCartButtonName = productsPage
                .addToCartRemoveFromCart(SAUCE_LABS_FLEECE_JACKET_NAME)
                .isAddButtonVisible(SAUCE_LABS_FLEECE_JACKET_NAME)
                .getAddToCartRemoveButtonName(SAUCE_LABS_FLEECE_JACKET_NAME);
        productsSteps.buttonNameShouldBeLike(addToCartButtonName, ADD_TO_CART_BUTTON_NAME);
    }

    @Test
    public void productCountInShoppingCartShouldBeIncreasedAfterProductAddingAndDecreasedAfterProductRemoving() {
        ProductsPage productsPage = safelyLogin();
        Set<String> productNames = PRODUCT_NAME_PRICE_MAP.keySet();
        productsSteps.productCountInShoppingCartShouldBeLikeAfterEachProductAddingRemoving(productsPage, productNames,
                0, true);
        productsSteps.productCountInShoppingCartShouldBeLikeAfterEachProductAddingRemoving(productsPage, productNames,
                6, false);
    }

    @Test
    public void emptyShoppingCartOpensAfterClickOnCart() {
        ProductsPage productsPage = safelyLogin();
        CartPage cartPage = productsPage
                .clickOnCart()
                .isPageOpened();
        cartSteps.cartPageShouldBeOpened(cartPage);
    }

    @Test
    public void shoppingCartWithOneProductOpensAfterClickOnCart() {
        ProductsPage productsPage = safelyLogin();
        productsPage
                .addToCartRemoveFromCart(SAUCE_LABS_FLEECE_JACKET_NAME);
        CartPage cartPage = productsPage
                .clickOnCart()
                .isPageOpened();
        cartSteps.cartPageShouldBeOpened(cartPage);
    }
}
