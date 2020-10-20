package by.teachmeskills.tests;

import by.teachmeskills.pages.ProductsPage;
import by.teachmeskills.tests.listeners.TestListener;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static by.teachmeskills.domain.Constants.*;

@Listeners(TestListener.class)
public class CartTest extends BaseTest {

    @Test(description = "Products page should be opened after continue shopping button clicking" +
            " if one product is added to cart")
    public void productsPageShouldBeOpenedAfterContinueShoppingButtonClickingIfOneProductAddedToCart() {
        ProductsPage productsPage = safelyLogin();
        productsPage
                .addToCartRemoveFromCart(SAUCE_LABS_FLEECE_JACKET_NAME);
        productsPage = cartPage
                .openPage()
                .isPageOpened()
                .clickOnContinueShoppingButton()
                .isPageOpened();
        String removeButtonName = productsPage
                .isRemoveButtonVisible(SAUCE_LABS_FLEECE_JACKET_NAME)
                .getAddToCartRemoveButtonName(SAUCE_LABS_FLEECE_JACKET_NAME);
        productsSteps.buttonNameShouldBeLike(removeButtonName, REMOVE_BUTTON_NAME);
        String actualProductCount = productsPage.getProductsNumberInShoppingCart();
        productsSteps.productNumberShouldBeLike(actualProductCount, "1");
    }

    @Test(description = "Products page should be opened after continue shopping button clicking" +
            " if no product is added to cart")
    public void productsPageShouldBeOpenedAfterContinueShoppingButtonClickingIfNoProductAddedToCart() {
        ProductsPage productsPage = safelyLogin();
        productsPage = cartPage
                .openPage()
                .isPageOpened()
                .clickOnContinueShoppingButton()
                .isPageOpened();
        String actualProductCount = productsPage.getProductsNumberInShoppingCart();
        productsSteps.productNumberShouldBeLike(actualProductCount, "0");
    }

    @Test(description = "Product number in shopping cart should be equal zero after removing one existed product")
    public void productNumberInShoppingCartShouldBeEqualZeroAfterRemovingOneExistedProduct() {
        ProductsPage productsPage = safelyLogin();
        productsPage
                .addToCartRemoveFromCart(SAUCE_LABS_FLEECE_JACKET_NAME);
        List<WebElement> productsInShoppingCart = cartPage
                .openPage()
                .isPageOpened()
                .clickOnRemoveButton(SAUCE_LABS_FLEECE_JACKET_NAME)
                .getProductsInShoppingCart();
        cartSteps.productNumberShouldBeLike(productsInShoppingCart.size(), 0);
    }

    @Test(description = "Product number in shopping cart should be equal one after removing one of" +
            " two products in cart")
    public void productNumberInShoppingCartShouldBeEqualOneAfterRemovingOneOfTwoProducts() {
        ProductsPage productsPage = safelyLogin();
        productsPage
                .addToCartRemoveFromCart(SAUCE_LABS_FLEECE_JACKET_NAME)
                .addToCartRemoveFromCart(SAUCE_LABS_BACKPACK_NAME);
        List<WebElement> productsInShoppingCart = cartPage
                .openPage()
                .isPageOpened()
                .clickOnRemoveButton(SAUCE_LABS_FLEECE_JACKET_NAME)
                .getProductsInShoppingCart();
        cartSteps.productNumberShouldBeLike(productsInShoppingCart.size(), 1);
        cartSteps.productsNameShouldBeLike(Collections.singletonList(SAUCE_LABS_BACKPACK_NAME),
                Collections.singletonList(SAUCE_LABS_BACKPACK_NAME));
    }
}
