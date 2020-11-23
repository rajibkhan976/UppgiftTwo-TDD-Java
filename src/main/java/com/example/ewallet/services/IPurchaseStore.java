package com.example.ewallet.services;

import com.example.ewallet.entities.Category;
import com.example.ewallet.entities.Purchase;

import java.util.Date;
import java.util.List;

public interface IPurchaseStore {

    /**
     * Get purchases from startDate to endDate.
     * @param start_date
     * @param end_date
     * @return Purchases.
     */
    List<Purchase> getPurchases(Date start_date, Date end_date);
    /**
     * Get purchases from startDate to endDate for category catId.
     * @param start_date
     * @param end_date
     * @param category_id
     * @return Purchases.
     */
    List<Purchase> getPurchasesByCategory(Date start_date, Date end_date, Integer category_id);
    /**
     * Get all categories.
     * @return Categories.
     */
    List<Category> getAllCategories();
}
