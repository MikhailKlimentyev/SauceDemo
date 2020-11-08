package by.teachmeskills.domain;

import java.util.HashMap;
import java.util.Map;

public final class Constants {

    public static final String BASE_URL = "https://www.saucedemo.com";

    public static final String SAUCE_LABS_FLEECE_JACKET_NAME = "Sauce Labs Fleece Jacket";
    public static final String SAUCE_LABS_BACKPACK_NAME = "Sauce Labs Backpack";
    public static final String SAUCE_LABS_BOLT_T_SHIRT_NAME = "Sauce Labs Bolt T-Shirt";
    public static final String SAUCE_LABS_ONESIE_NAME = "Sauce Labs Onesie";
    public static final String SAUCE_LABS_BIKE_LIGHT_NAME = "Sauce Labs Bike Light";
    public static final String TEST_ALL_THE_THINGS_T_SHIRT_RED_NAME = "Test.allTheThings() T-Shirt (Red)";

    public static final String LOCKED_USER_ERROR_MESSAGE = "Epic sadface: Sorry, this user has been locked out.";
    public static final String INVALID_USER_NAME_PASSWORD_ERROR_MESSAGE =
            "Epic sadface: Username and password do not match any user in this service";

    public static final String SAUCE_LABS_FLEECE_JACKET_PRICE = "$49.99";
    public static final String SAUCE_LABS_BACKPACK_PRICE = "$29.99";
    public static final String SAUCE_LABS_BOLT_T_SHIRT_PRICE = "$15.99";
    public static final String SAUCE_LABS_ONESIE_PRICE = "$7.99";
    public static final String SAUCE_LABS_BIKE_LIGHT_PRICE = "$9.99";
    public static final String TEST_ALL_THE_THINGS_T_SHIRT_RED_PRICE = "$15.99";

    public static final String AZ_DROPDOWN_VALUE = "az";
    public static final String ZA_DROPDOWN_VALUE = "za";
    public static final String LOHI_DROPDOWN_VALUE = "lohi";
    public static final String HILO_DROPDOWN_VALUE = "hilo";

    public static final String NAME_SORTING_PARAMETER = "name";
    public static final String PRICE_SORTING_PARAMETER = "price";

    public static final String REMOVE_BUTTON_NAME = "REMOVE";
    public static final String ADD_TO_CART_BUTTON_NAME = "ADD TO CART";

    public static final String FIRST_NAME = "Francesco";
    public static final String LAST_MANE = "Totti";
    public static final String POSTAL_CODE = "121212333";

    public static final String SAUCE_DEMO_USER_PROPERTY = "SauceDemoUser";
    public static final String SAUCE_DEMO_PASS_PROPERTY = "SauceDemoPass";
    public static final String SAUCE_DEMO_LOCKED_USER_PROPERTY = "SauceDemoLockedUser";

    public static final String THANK_YOU_FOR_YOUR_ORDER_MESSAGE = "THANK YOU FOR YOUR ORDER\n" +
            "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

    public static final Map<String, Double> PRODUCT_NAME_PRICE_MAP = new HashMap<String, Double>() {{
        put(SAUCE_LABS_FLEECE_JACKET_NAME, Double.parseDouble(SAUCE_LABS_FLEECE_JACKET_PRICE.substring(1)));
        put(SAUCE_LABS_BACKPACK_NAME, Double.parseDouble(SAUCE_LABS_BACKPACK_PRICE.substring(1)));
        put(SAUCE_LABS_BOLT_T_SHIRT_NAME, Double.parseDouble(SAUCE_LABS_BOLT_T_SHIRT_PRICE.substring(1)));
        put(SAUCE_LABS_ONESIE_NAME, Double.parseDouble(SAUCE_LABS_ONESIE_PRICE.substring(1)));
        put(SAUCE_LABS_BIKE_LIGHT_NAME, Double.parseDouble(SAUCE_LABS_BIKE_LIGHT_PRICE.substring(1)));
        put(TEST_ALL_THE_THINGS_T_SHIRT_RED_NAME, Double.parseDouble(TEST_ALL_THE_THINGS_T_SHIRT_RED_PRICE.substring(1)));
    }};

    private Constants() {
        throw new AssertionError("This class should never be instantiated.");
    }
}
