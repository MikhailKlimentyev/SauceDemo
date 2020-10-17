package by.teachmeskills.steps;

import by.teachmeskills.pages.ProductsPage;

import java.util.*;
import java.util.Map.Entry;

import static java.util.stream.Collectors.toMap;
import static org.testng.Assert.assertEquals;

public class ProductsSteps {

    public void productsLabelShouldBeLike(String actualLabel, String expectedLabel) {
        assertEquals(actualLabel, expectedLabel, "Label is not correct");
    }

    public void productsNumberShouldBeLike(int actualNumber, int expectedNumber) {
        assertEquals(actualNumber, expectedNumber, "Products number is not correct");
    }

    public void buttonNameShouldBeLike(String actualButtonName, String expectedButtonName) {
        assertEquals(actualButtonName, expectedButtonName, "Button name is not correct");
    }

    public void productNamesAndPricesShouldBeLike(Map<String, String> actualProductNamesPricesMap,
                                                  Map<String, String> expectedProductNamesPricesMap) {
        assertEquals(actualProductNamesPricesMap.size(), expectedProductNamesPricesMap.size(),
                "Actual product quantity do not match expected");
        Iterator actualEntries = actualProductNamesPricesMap.entrySet().iterator();
        Iterator expectedEntries = expectedProductNamesPricesMap.entrySet().iterator();
        while (actualEntries.hasNext() && expectedEntries.hasNext()) {
            Entry actualEntry = (Entry) actualEntries.next();
            Entry expectedEntry = (Entry) expectedEntries.next();
            assertEquals(actualEntry.getKey(), expectedEntry.getKey(), "Keys do not coincide");
            assertEquals(actualEntry.getValue(), expectedEntry.getValue(), "Values do not coincide");
        }
    }

    public void productCountInShoppingCartShouldBeLikeAfterEachProductAddingRemoving(ProductsPage productsPage,
                                                                                     Set<String> productNames,
                                                                                     int startProductNumber,
                                                                                     boolean isIncreaseCount) {
        int expectedProductCount = startProductNumber;
        for (String productName : productNames) {
            productsPage.addToCartRemoveFromCart(productName);
            String actualProductCount = productsPage.getProductsNumberInShoppingCart();
            expectedProductCount = increaseOrDecreaseCount(isIncreaseCount, expectedProductCount);
            productNumberShouldBeLike(actualProductCount, String.valueOf(expectedProductCount));
        }
    }

    public int increaseOrDecreaseCount(boolean isIncreaseCount, int count) {
        return isIncreaseCount ? ++count : --count;
    }

    public void productNumberShouldBeLike(String actualProductNumber, String expectedProductNumber) {
        if (expectedProductNumber.equals("0")) {
            expectedProductNumber = "";
        }
        assertEquals(actualProductNumber, expectedProductNumber);
    }

    public Map<String, String> sortProducts(Map<String, Double> productsNamePriceMap, String sortingParameter,
                                            boolean sortingOrder) {
        Map<String, Double> stringDoubleMap = sortMap(productsNamePriceMap, getComparator(sortingParameter,
                sortingOrder));
        String dollar = "$";
        return stringDoubleMap.entrySet()
                .stream()
                .collect(toMap(entry -> entry.getKey(),
                        entry -> dollar.concat(String.valueOf(entry.getValue())),
                        (entry1, entry2) -> entry1,
                        LinkedHashMap::new));
    }

    private Map<String, Double> sortMap(Map<String, Double> mapToBeSorted,
                                        Comparator<Entry<String, Double>> comparator) {
        return mapToBeSorted.entrySet()
                .stream()
                .sorted(comparator)
                .collect(toMap(entry -> entry.getKey(),
                        entry -> entry.getValue(),
                        (entry1, entry2) -> entry1,
                        LinkedHashMap::new));
    }

    private Comparator<Entry<String, Double>> getComparator(String sortingParameter, boolean sortingOrder) {
        switch (sortingParameter) {
            case "name":
                Comparator<Entry<String, Double>> entryComparatorByName = Entry.<String, Double>comparingByKey();
                return sortingOrder ? entryComparatorByName : Collections.reverseOrder(entryComparatorByName);
            case "price":
                Comparator<Entry<String, Double>> entryComparatorByPrice = Entry.<String, Double>comparingByValue()
                        .thenComparing(Entry.comparingByKey((key1, key2) -> key1.length() - key2.length()));
                return sortingOrder ? entryComparatorByPrice : Collections.reverseOrder(entryComparatorByPrice);
            default:
                throw new AssertionError("invalid " + sortingParameter);
        }
    }
}
