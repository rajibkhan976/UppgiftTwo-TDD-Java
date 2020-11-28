package com.example.ewallet.services;

import com.example.ewallet.entities.Category;
import com.example.ewallet.entities.Purchase;
import com.example.ewallet.repositories.CategoryRepository;
import com.example.ewallet.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PurchaseStore implements IPurchaseStore {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Purchase> getPurchases(LocalDate startdate, LocalDate enddate) {
        List<Purchase> purchases = purchaseRepository.findAll();

        if (startdate != null) {
            purchases = purchases.stream()
                    .filter(purchase -> purchase.getStartdate() == startdate)
                    .collect(Collectors.toList());
        }

        if (enddate != null) {
            purchases = purchases.stream()
                    .filter(purchase -> purchase.getEnddate() == enddate)
                    .collect(Collectors.toList());
        }
        return purchases;
    }

    @Override
    public List<Purchase> getPurchasesByCategory(LocalDate startdate, LocalDate enddate, Integer categoryid) {

        List<Purchase> purchases = purchaseRepository.findByCategoryid(categoryid);

        if (startdate != null) {
            purchases = purchases.stream()
                    .filter(purchase -> purchase.getStartdate() == startdate)
                    .collect(Collectors.toList());
        }

        if (enddate != null) {
            purchases = purchases.stream()
                    .filter(purchase -> purchase.getEnddate() == enddate)
                    .collect(Collectors.toList());
        }
        return purchases;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
