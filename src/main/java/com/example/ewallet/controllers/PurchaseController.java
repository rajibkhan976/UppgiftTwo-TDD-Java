package com.example.ewallet.controllers;

import com.example.ewallet.entities.Category;
import com.example.ewallet.entities.Purchase;
import com.example.ewallet.services.PurchaseStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseStore purchaseStore;

    @GetMapping("")
    public List<Purchase> getAllPurchases(@RequestParam(required = false) LocalDate startdate, @RequestParam(required = false) LocalDate enddate) {
        return purchaseStore.getPurchases(startdate, enddate);
    }

    @GetMapping("/{categoryid}")
    public List<Purchase> getPurchasesByCategory(@RequestParam(required = false) LocalDate startdate, @RequestParam(required = false) LocalDate enddate, @PathVariable Integer categoryid) {
        return  purchaseStore.getPurchasesByCategory(startdate, enddate, categoryid);
    }

    @GetMapping("/")
    public List<Category> getAllCategories() {
        return purchaseStore.getAllCategories();
    }
}
