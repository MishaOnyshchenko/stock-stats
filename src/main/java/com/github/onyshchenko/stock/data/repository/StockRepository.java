package com.github.onyshchenko.stock.data.repository;

import com.github.onyshchenko.stock.data.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
