package com.github.onyshchenko.stock.service;

import com.github.onyshchenko.stock.data.domain.Stock;

import java.util.List;
import java.util.Queue;

public interface StockService {

    void createStocks(Queue<String> urls);

    Stock createStock(String stockDefinition);

    List<Stock> getHighestValues(int limit);

    List<Stock> getGreatestChanges(int limit);
}
