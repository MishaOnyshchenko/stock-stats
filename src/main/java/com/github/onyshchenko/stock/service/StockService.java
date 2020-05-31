package com.github.onyshchenko.stock.service;

import com.github.onyshchenko.stock.data.domain.Stock;
import com.github.onyshchenko.stock.data.domain.Symbol;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Queue;
import java.util.Set;

public interface StockService {

    Queue<String> getUrls(Set<Symbol> symbols);

    List<String> getStockDefinitions(Queue<String> urls);

    void createStocks(Queue<String> urls);

    @Transactional
    Stock createStock(String stockDefinition);
}
