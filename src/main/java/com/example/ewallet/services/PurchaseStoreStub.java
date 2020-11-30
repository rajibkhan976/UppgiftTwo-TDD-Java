package com.example.ewallet.services;

import com.example.ewallet.entities.Category;
import com.example.ewallet.entities.Purchase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseStoreStub extends PurchaseStore {

    List<Purchase> purchases = new ArrayList<Purchase>();
    List<Category> categories = new ArrayList<Category>();

    public void addPurchases(Integer id, LocalDate startdate, LocalDate enddate, double amount, String comment, Integer categoryid) {

        Purchase purchase = new Purchase(
                id, startdate, enddate, amount, comment, categoryid);

        purchases.add(purchase);
    }

    public void  addCategories(Integer id, String description) {
        Category category = new Category(id, description);

        categories.add(category);
    }

    @Override
    public List<Purchase> getPurchases(LocalDate startdate, LocalDate enddate) {
        return purchases;
    }

    @Override
    public List<Purchase> getPurchasesByCategory(LocalDate startdate, LocalDate enddate, Integer categoryid) {
        return purchases.stream()
                .filter(purchase -> purchase.getCategoryid() == categoryid)
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }
}
