package com.example.ewallet.repositories;

import com.example.ewallet.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    Optional<List<Purchase>> findByCategory(Date start_date, Date end_date, Integer category_id);
}
