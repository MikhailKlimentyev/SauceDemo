package by.teachmeskills.tests;

import by.teachmeskills.pages.CartPage;
import by.teachmeskills.pages.ProductsPage;
import by.teachmeskills.tests.listeners.TestListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

import static by.teachmeskills.domain.Constants.*;

@Listeners(TestListener.class)
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

    @Test(description = "Product should be added into cart")
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

    @Test(description = "Products should be sorted by name and by price in direct and reverse order",
            dataProvider = "dropdownValuesSortingParametersDataProvider")
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

    @Test(description = "Button to add remove product should change name after adding or removing product")
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

    @Test(description = "Product count in shopping cart should be increased" +
            "after product adding and decreased after product removing")
    public void productCountInShoppingCartShouldBeIncreasedAfterProductAddingAndDecreasedAfterProductRemoving() {
        ProductsPage productsPage = safelyLogin();
        Set<String> productNames = PRODUCT_NAME_PRICE_MAP.keySet();
        productsSteps.productCountInShoppingCartShouldBeLikeAfterEachProductAddingRemoving(productsPage, productNames,
                0, true);
        productsSteps.productCountInShoppingCartShouldBeLikeAfterEachProductAddingRemoving(productsPage, productNames,
                6, false);
    }

    @Test(description = "Empty shopping cart opens after click on cart")
    public void emptyShoppingCartOpensAfterClickOnCart() {
        ProductsPage productsPage = safelyLogin();
        CartPage cartPage = productsPage
                .clickOnCart()
                .isPageOpened();
        cartSteps.cartPageShouldBeOpened(cartPage);
    }

    @Test(description = "Shopping cart with one product opens after click on cart")
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
