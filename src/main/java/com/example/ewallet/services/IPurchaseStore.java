package com.example.ewallet.services;

import com.example.ewallet.entities.Category;
import com.example.ewallet.entities.Purchase;

import java.time.LocalDate;
import java.util.List;

public interface IPurchaseStore {

    /**
     * Get purchases from startDate to endDate.
     * @param startdate
     * @param enddate
     * @return Purchases.
     */
    List<Purchase> getPurchases(LocalDate startdate, LocalDate enddate);
    /**
     * Get purchases from startDate to endDate for category catId.
     * @param startdate
     * @param enddate
     * @param categoryid
     * @return Purchases.
     */
    List<Purchase> getPurchasesByCategory(LocalDate startdate, LocalDate enddate, Integer categoryid);
    /**
     * Get all categories.
     * @return Categories.
     */
    List<Category> getAllCategories();
}
