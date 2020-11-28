package com.example.ewallet.services;

import com.example.ewallet.entities.Purchase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseStoreStub extends PurchaseStore {

    List<Purchase> entries = new ArrayList<Purchase>();

    public void addPurchases(Integer id, LocalDate startdate, LocalDate enddate, double amount, String comment, Integer categoryid) {

        Purchase purchase = new Purchase(
                id, startdate, enddate, amount, comment, categoryid);

        entries.add(purchase);
    }

    @Override
    public List<Purchase> getPurchases(LocalDate startdate, LocalDate enddate) {
        return entries;
    }
}
