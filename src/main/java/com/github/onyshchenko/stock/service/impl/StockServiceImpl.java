package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Stock;
import com.github.onyshchenko.stock.data.domain.Symbol;
import com.github.onyshchenko.stock.data.repository.StockRepository;
import com.github.onyshchenko.stock.service.ApiService;
import com.github.onyshchenko.stock.service.StockService;
import com.github.onyshchenko.stock.utils.mappers.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private ApiService apiService;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockRepository stockRepository;


    @Override
    public Queue<String> getUrls(Set<Symbol> symbols) {
        BlockingQueue<String> urls = new LinkedBlockingQueue<>();
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

    @Transactional
    @Override
    public void createStocks(Queue<String> urls) {
        while (!urls.isEmpty()) {
            String stockDefinition = apiService.call(urls.poll());
            Stock stock = stockMapper.toStock(stockDefinition);
            save(stock);
        }
    }

    @Transactional
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Transactional
    public Stock get(Long id) {
        return stockRepository.getOne(id);
    }
}
