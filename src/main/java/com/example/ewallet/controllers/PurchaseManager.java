package com.example.ewallet.controllers;

import com.example.ewallet.entities.Purchase;
import com.example.ewallet.services.PurchaseStore;
import com.example.ewallet.services.PurchaseStoreStub;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PurchaseManager implements IPurchaseManager {

    private PurchaseStore store = null;

    public PurchaseManager (PurchaseStore purchaseStore) {
        this.store = purchaseStore;
    }

    @Override
    public float sumOfMonth(int year, int month) {

        LocalDate start_date;
        LocalDate end_date;
        float sumOfMonth = 0;
        if (year > 2000 && month > 0 && month < 13) {
            if (month != 2) {
                start_date = LocalDate.of(year, month, 1);
                end_date = LocalDate.of(year, month, 30);
            } else {
                start_date = LocalDate.of(year, month, 1);
                end_date = LocalDate.of(year, month, 28);
            }

            List<Purchase> purchases = store.getPurchases(start_date, end_date);

            if (purchases != null) {
                for (Purchase purchase: purchases) {
                    sumOfMonth += purchase.getAmount();
                }
            }
        }
        return sumOfMonth;
    }

    @Override
    public float[] monthlyAverage(int year) {

        float[] monthlyAverageOfYear = new float[12];
        int[] monthArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        LocalDate start_date;
        LocalDate end_date;
        float sumOfMonth = 0;
        if (year > 2000) {
            for (int i = 0; i < monthArr.length; i++) {

                if (monthArr[i] == 2) {
                    start_date = LocalDate.of(year, monthArr[i], 1);
                    end_date = LocalDate.of(year, monthArr[i], 28);
                } else {
                    start_date = LocalDate.of(year, monthArr[i], 1);
                    end_date = LocalDate.of(year, monthArr[i], 30);
                }

                System.out.println(start_date.getMonth());
                System.out.println(end_date.getMonth());

                List<Purchase> purchases = store.getPurchases(start_date, end_date);

                System.out.println(purchases);

                if (purchases != null) {
                    for (Purchase purchase: purchases) {
                        System.out.println(purchase.getAmount());
                        sumOfMonth += purchase.getAmount();
                    }
                    System.out.println(sumOfMonth / 30);
                    monthlyAverageOfYear[i] = sumOfMonth / 30;
                } else {
                    //System.out.println(sumOfMonth);
                    monthlyAverageOfYear[i] = sumOfMonth;
                }
            }
        }
        return monthlyAverageOfYear;
    }
}
