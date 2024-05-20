package com.venture.suyaho.deal.repository;

import com.venture.suyaho.deal.entity.Category;
import com.venture.suyaho.deal.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findByCategory(Category category);
}