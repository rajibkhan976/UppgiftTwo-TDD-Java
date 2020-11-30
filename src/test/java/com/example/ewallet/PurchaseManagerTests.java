package com.example.ewallet;

import com.example.ewallet.controllers.PurchaseManager;
import com.example.ewallet.entities.Category;
import com.example.ewallet.entities.Purchase;
import com.example.ewallet.services.PurchaseStore;
import com.example.ewallet.services.PurchaseStoreStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@SpringBootTest
public class PurchaseManagerTests {

    @Test
    public void testSumOfMonth () {

        PurchaseStoreStub storeStub = new PurchaseStoreStub();
        PurchaseManager purchaseManager = new PurchaseManager(storeStub);

        storeStub.addPurchases(1, LocalDate.parse("2020-11-01"), LocalDate.parse("2020-11-01"), 500.0, "Vegetables", 4);
        storeStub.addPurchases(2, LocalDate.parse("2020-11-15"), LocalDate.parse("2020-11-30"), 1500.0, "Electronics", 5);
        storeStub.addPurchases(3, LocalDate.parse("2020-11-16"), LocalDate.parse("2020-11-20"), 1000.0, "Furniture", 6);

        Assertions.assertEquals(purchaseManager.sumOfMonth(2020,11), 3000);
    }

    @Test
    public void testSumOfMonthWithWrongAnswer () {

        PurchaseStoreStub storeStub = new PurchaseStoreStub();
        PurchaseManager purchaseManager = new PurchaseManager(storeStub);

        storeStub.addPurchases(4, LocalDate.parse("2020-11-01"), LocalDate.parse("2020-11-01"), 500.0, "Vegetables", 4);
        storeStub.addPurchases(5, LocalDate.parse("2020-11-02"), LocalDate.parse("2020-11-02"), 1500.0, "Electronics", 5);
        storeStub.addPurchases(6, LocalDate.parse("2020-11-11"), LocalDate.parse("2020-11-11"), 1000.0, "Furniture", 6);

        Assertions.assertFalse(purchaseManager.sumOfMonth(2020,11) == 2000);
    }

    @Test
    public void  testMonthlyAverage () {

        PurchaseStoreStub storeStub = new PurchaseStoreStub();
        PurchaseManager purchaseManager = new PurchaseManager(storeStub);

        storeStub.addPurchases(9, LocalDate.of(2020,10,1), LocalDate.of(2020,10,31), 4500.0, "Furniture bfbn", 6);
        storeStub.addPurchases(7, LocalDate.of(2020,11,1), LocalDate.of(2020,11,30), 3000.0, "Vegetables dsb", 4);
        storeStub.addPurchases(11, LocalDate.of(2020,12,1), LocalDate.of(2020,12,31), 6000.0, "Electronics bdb", 5);

        float[] expectedAverage = {0, 0, 0, 0, 0, 0, 0, 0, 0, 150, 100, 200};

        Assertions.assertArrayEquals(expectedAverage, purchaseManager.monthlyAverage(2020));
    }

    @Test
    public void testYearlyAveragePerCategory () {

        PurchaseStoreStub storeStub = new PurchaseStoreStub();
        PurchaseManager purchaseManager = new PurchaseManager(storeStub);

        storeStub.addCategories(4, "Vegetables");
        storeStub.addCategories(5, "Electronics");
        storeStub.addCategories(6, "Furniture");

        storeStub.addPurchases(1, LocalDate.parse("2020-11-01"), LocalDate.parse("2020-11-01"), 21500.0, "Vegetables", 4);
        storeStub.addPurchases(2, LocalDate.parse("2020-11-15"), LocalDate.parse("2020-11-30"), 20000.0, "Electronics", 5);
        storeStub.addPurchases(3, LocalDate.parse("2020-11-16"), LocalDate.parse("2020-11-20"), 23800.0, "Furniture", 6);

        storeStub.addPurchases(4, LocalDate.parse("2020-11-01"), LocalDate.parse("2020-11-01"), 15000.0, "Vegetables", 4);
        storeStub.addPurchases(5, LocalDate.parse("2020-11-02"), LocalDate.parse("2020-11-02"), 20150.0, "Electronics", 5);
        storeStub.addPurchases(6, LocalDate.parse("2020-11-11"), LocalDate.parse("2020-11-11"), 20000.0, "Furniture", 6);

        float[] expectedYearlyPerCatAvg = {100, 110, 120};

        Assertions.assertArrayEquals(expectedYearlyPerCatAvg, purchaseManager.yearlyAveragePerCategory(2020));
    }

    @Test
    public void testSumOfMonthWithMockito () {
        PurchaseStore purchaseStore = Mockito.mock(PurchaseStore.class);
        PurchaseManager purchaseManager = new PurchaseManager(purchaseStore);

        Purchase[] purchases = new Purchase[]{
            new Purchase(1, LocalDate.parse("2020-11-01"), LocalDate.parse("2020-11-01"), 500.0, "Vegetables", 4),
            new Purchase(2, LocalDate.parse("2020-11-15"), LocalDate.parse("2020-11-30"), 1500.0, "Electronics", 5),
            new Purchase(3, LocalDate.parse("2020-11-16"), LocalDate.parse("2020-11-20"), 1000.0, "Furniture", 6)
        };

        when(purchaseStore.getPurchases(LocalDate.of(2020, 11, 1), LocalDate.of(2020, 11, 30)))
                .thenReturn(Arrays.stream(purchases).collect(Collectors.toList()));

        Assertions.assertEquals(purchaseManager.sumOfMonth(2020,11), 3000);
    }

    @Test
    public void testMonthlyAverageWithMockito() {

        PurchaseStore purchaseStore = Mockito.mock(PurchaseStore.class);
        PurchaseManager purchaseManager = new PurchaseManager(purchaseStore);

        Purchase[] purchases = new Purchase[]{
                new Purchase(9, LocalDate.of(2020,11,1), LocalDate.of(2020,11,10), 3000.0, "Furniture bfbn", 6),
                new Purchase(7, LocalDate.of(2020,11,11), LocalDate.of(2020,11,20), 3000.0, "Vegetables dsb", 4),
                new Purchase(11, LocalDate.of(2020,11,21), LocalDate.of(2020,11,30), 6000.0, "Electronics bdb", 5)
        };

        when(purchaseStore.getPurchases(LocalDate.of(2020, 11, 1), LocalDate.of(2020, 11, 30)))
                .thenReturn(Arrays.stream(purchases).collect(Collectors.toList()));

        float[] expectedAverage = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 400, 0};

        Assertions.assertArrayEquals(expectedAverage, purchaseManager.monthlyAverage(2020));
    }

    @Test
    public void testYearlyAveragePerCategoryWithMockito() {
        PurchaseStore purchaseStore = Mockito.mock(PurchaseStore.class);
        PurchaseManager purchaseManager = new PurchaseManager(purchaseStore);

        Category[] categories = new Category[] {
              new Category(4, "Vegetables"),
                new Category(5, "Electronics"),
                new Category(6, "Furniture"),
        };

        Purchase[] purchases = new Purchase[] {
            new Purchase(1, LocalDate.parse("2020-11-01"), LocalDate.parse("2020-11-01"), 500.0, "Vegetables", 4),
            new Purchase(2, LocalDate.parse("2020-11-15"), LocalDate.parse("2020-11-30"), 150.0, "Electronics", 4),
            new Purchase(3, LocalDate.parse("2020-11-16"), LocalDate.parse("2020-11-20"), 1000.0, "Furniture", 4),
            new Purchase(4, LocalDate.parse("2020-11-01"), LocalDate.parse("2020-11-01"), 100.0, "Vegetables", 4),
            new Purchase(5, LocalDate.parse("2020-11-02"), LocalDate.parse("2020-11-02"), 100.0, "Electronics", 4),
            new Purchase(6, LocalDate.parse("2020-11-11"), LocalDate.parse("2020-11-11"), 1800.0, "Furniture", 4)
        };

        when(purchaseStore.getAllCategories())
                .thenReturn(Arrays.stream(categories).collect(Collectors.toList()));

        when(purchaseStore.getPurchasesByCategory(LocalDate.of(2020,1,1), LocalDate.of(2020,12,31), 4))
                .thenReturn(Arrays.stream(purchases).collect(Collectors.toList()));

        float[] expectedYearlyPerCatAvg = {10, 0, 0};

        Assertions.assertArrayEquals(expectedYearlyPerCatAvg, purchaseManager.yearlyAveragePerCategory(2020));
    }
}
