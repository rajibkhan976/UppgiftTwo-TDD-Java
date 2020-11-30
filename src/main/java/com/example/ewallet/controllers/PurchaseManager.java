package com.example.ewallet.controllers;

import com.example.ewallet.entities.Category;
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
            if (month == 2) {
                start_date = LocalDate.of(year, month, 1);
                end_date = LocalDate.of(year, month, 28);
            } else if (month == 1 ||
                    month == 3 ||
                    month == 2 ||
                    month == 5 ||
                    month == 7 ||
                    month == 8 ||
                    month == 10 ||
                    month == 12
            ) {
                start_date = LocalDate.of(year, month, 1);
                end_date = LocalDate.of(year, month, 31);
            } else {
                start_date = LocalDate.of(year, month, 1);
                end_date = LocalDate.of(year, month, 30);
            }

            List<Purchase> purchases = store.getPurchases(start_date, end_date);

            if (purchases != null) {
                for (Purchase purchase: purchases) {
                    if (purchase.getStartdate().getMonthValue() == start_date.getMonthValue() &&
                            purchase.getStartdate().getDayOfMonth() >= start_date.getDayOfMonth() &&
                            purchase.getEnddate().getMonthValue() == end_date.getMonthValue() &&
                            purchase.getEnddate().getDayOfMonth() <= end_date.getDayOfMonth()
                    ) {
                        sumOfMonth += purchase.getAmount();
                    }
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

        if (year > 2000) {
            for (int i = 0; i < monthArr.length; i++) {

                if (monthArr[i] == 2) {
                    start_date = LocalDate.of(year, monthArr[i], 1);
                    end_date = LocalDate.of(year, monthArr[i], 28);
                } else if (monthArr[i] == 1 ||
                        monthArr[i] == 3 ||
                        monthArr[i] == 2 ||
                        monthArr[i] == 5 ||
                        monthArr[i] == 7 ||
                        monthArr[i] == 8 ||
                        monthArr[i] == 10 ||
                        monthArr[i] == 12
                ) {
                    start_date = LocalDate.of(year, monthArr[i], 1);
                    end_date = LocalDate.of(year, monthArr[i], 31);
                } else {
                    start_date = LocalDate.of(year, monthArr[i], 1);
                    end_date = LocalDate.of(year, monthArr[i], 30);
                }

                List<Purchase> purchases = store.getPurchases(start_date, end_date);

                float sumOfMonth = 0;

                if (purchases != null) {
                    for (Purchase purchase: purchases) {
                        if (purchase.getStartdate().getMonthValue() == start_date.getMonthValue() &&
                                purchase.getStartdate().getDayOfMonth() >= start_date.getDayOfMonth() &&
                                purchase.getEnddate().getMonthValue() == end_date.getMonthValue() &&
                                purchase.getEnddate().getDayOfMonth() <= end_date.getDayOfMonth()
                        ) {
                            sumOfMonth += purchase.getAmount();
                        }
                    }
                    monthlyAverageOfYear[i] = sumOfMonth / 30;
                } else {
                    monthlyAverageOfYear[i] = sumOfMonth;
                }
            }
        }
        return monthlyAverageOfYear;
    }

    @Override
    public float[] yearlyAveragePerCategory(int year) {

        List<Category> categoryArr = store.getAllCategories();
        float[] yearlyAvgPerCatgArr = new float[categoryArr.size()];
        LocalDate start_date;
        LocalDate end_date;

        int yearlyAvgPerCatgArrIndexCount = 0;

        if (!(year < 2000)) {
            start_date = LocalDate.of(year, 1, 1);
            end_date = LocalDate.of(year, 12, 31);

            for(Category category: categoryArr) {
                List<Purchase> purchases = store.getPurchasesByCategory(start_date, end_date, category.getId());
                float sumOfYear = 0;
                for (Purchase purchase: purchases) {
                    sumOfYear += purchase.getAmount();
                }
                yearlyAvgPerCatgArr[yearlyAvgPerCatgArrIndexCount] = sumOfYear / 365;
                yearlyAvgPerCatgArrIndexCount++;
            }
        }
        return yearlyAvgPerCatgArr;
    }
}
