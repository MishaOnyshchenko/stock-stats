package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Symbol;
import com.github.onyshchenko.stock.service.ApiService;
import com.github.onyshchenko.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private ApiService apiService;

    @Override
    public Queue<String> getUrls(Set<Symbol> symbols) {
        Queue<String> urls = new ConcurrentLinkedQueue<>();
        for (Symbol symbol : symbols) {
            String stockUrl = String.format("https://sandbox.iexapis.com/stable/stock/%s/quote?token=Tpk_ee567917a6b640bb8602834c9d30e571", symbol.getSymbol());
            urls.add(stockUrl);
        }
        return urls;
    }

    @Override
    public List<String> getStockDefinitions(Queue<String> urls) {
        return urls.parallelStream().map(u -> apiService.call(u)).collect(Collectors.toList());
    }
}
