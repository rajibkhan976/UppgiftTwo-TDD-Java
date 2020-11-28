package com.example.ewallet;

import com.example.ewallet.controllers.PurchaseManager;
import com.example.ewallet.services.PurchaseStoreStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class PurchaseManagerTests {

    @Test
    public void testSumOfMonth () {
        PurchaseStoreStub storeStub = new PurchaseStoreStub();
        PurchaseManager purchaseManager = new PurchaseManager(storeStub);

        storeStub.addPurchases(1, LocalDate.parse("2020-11-01"), LocalDate.parse("2020-11-01"), 500.0, "Vegetables", 4);
        storeStub.addPurchases(2, LocalDate.parse("2020-11-15"), LocalDate.parse("2020-11-01"), 1500.0, "Electronics", 5);
        storeStub.addPurchases(3, LocalDate.parse("2020-11-30"), LocalDate.parse("2020-11-01"), 1000.0, "Furniture", 6);
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
    public void  testmonthlyAverage () {

        PurchaseStoreStub storeStub = new PurchaseStoreStub();
        PurchaseManager purchaseManager = new PurchaseManager(storeStub);

        storeStub.addPurchases(7, LocalDate.of(2020,11,1), LocalDate.of(2020,11,30), 3000.0, "Vegetables dsb", 4);
        storeStub.addPurchases(9, LocalDate.of(2020,10,1), LocalDate.of(2020,10,31), 4500.0, "Furniture bfbn", 6);
        storeStub.addPurchases(11, LocalDate.of(2020,12,1), LocalDate.of(2020,12,31), 6000.0, "Electronics bdb", 5);

        float[] expectedAverage = {0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 150, 200};

        Assertions.assertArrayEquals(expectedAverage, purchaseManager.monthlyAverage(2020));
    }
}
