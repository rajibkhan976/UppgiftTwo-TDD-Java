package com.example.ewallet.repositories;

import com.example.ewallet.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByCategoryid(Integer categoryid);
}
