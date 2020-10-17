package by.teachmeskills.steps;

import by.teachmeskills.pages.CartPage;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartSteps {

    public void productDetailsShouldBeLike(String actualProductPrice, String actualProductQuantity,
                                           String expectedProductPrice, String expectedProductQuantity) {
        assertEquals(actualProductPrice, expectedProductPrice, "Price is not correct");
        assertEquals(actualProductQuantity, expectedProductQuantity, "Quantity is not correct");
    }

    public void productNumberShouldBeLike(int actualProductNumber, int expectedProductNumber) {
        assertEquals(actualProductNumber, expectedProductNumber, "Product number is not expected");
    }

    public void productsNameShouldBeLike(List<String> actualProductNames, List<String> expectedProductNames) {
        assertEquals(actualProductNames.size(), expectedProductNames.size(), "actual product names count" +
                " do not match expected product names count");
        Collections.sort(actualProductNames);
        Collections.sort(expectedProductNames);
        for (int index = 0; index < expectedProductNames.size(); index++) {
            assertEquals(expectedProductNames.get(0), actualProductNames.get(0), "Product name" +
                    " do not match expected");
        }
    }

    public void cartPageShouldBeOpened(CartPage cartPage) {
        boolean actualResult = false;
        if (cartPage != null) {
            actualResult = true;
        }
        assertTrue(actualResult, "Cart page is not opened");
    }
}
