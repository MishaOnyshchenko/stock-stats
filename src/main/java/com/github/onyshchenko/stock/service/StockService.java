package com.github.onyshchenko.stock.service;

import com.github.onyshchenko.stock.data.domain.Stock;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Queue;

public interface StockService {

    void createStocks(Queue<String> urls);

    Stock createStock(String stockDefinition);

    Stock save(Stock stock);

    @Transactional
    void updateStock(Long id, Stock newStock);

    List<Stock> getHighestValues(int limit);

    List<Stock> getGreatestChanges(int limit);
}
