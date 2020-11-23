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
import java.util.Date;
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
    public List<Purchase> getPurchases(Date start_date, Date end_date) {
        List<Purchase> purchases = purchaseRepository.findAll();

        if (start_date != null) {
            purchases = purchases.stream()
                    .filter(purchase -> purchase.getStart_date() == start_date)
                    .collect(Collectors.toList());
        }

        if (end_date != null) {
            purchases = purchases.stream()
                    .filter(purchase -> purchase.getEnd_date() == end_date)
                    .collect(Collectors.toList());
        }
        return purchases;
    }

    @Override
    public List<Purchase> getPurchasesByCategory(Date start_date, Date end_date, Integer category_id) {
        return purchaseRepository.findByCategory(start_date, end_date, category_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Could not find the purchase by id", category_id)));
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
