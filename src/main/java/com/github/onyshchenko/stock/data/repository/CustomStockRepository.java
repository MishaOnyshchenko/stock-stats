package com.github.onyshchenko.stock.data.repository;

import com.github.onyshchenko.stock.data.domain.Stock;

import java.util.List;

public interface CustomStockRepository {

    List<Stock> findGreatestValueOrderedByCloseAndCompanyName(int limit);

    List<Stock> findOrderedByGreatestChange(int limit);
}
