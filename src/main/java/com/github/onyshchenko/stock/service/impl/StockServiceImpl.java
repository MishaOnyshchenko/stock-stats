package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Stock;
import com.github.onyshchenko.stock.data.repository.StockRepository;
import com.github.onyshchenko.stock.service.ApiService;
import com.github.onyshchenko.stock.service.StockService;
import com.github.onyshchenko.stock.utils.mappers.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Queue;

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
    public void createStocks(Queue<String> urls) {
        while (!urls.isEmpty()) {
            String url = urls.poll();
            String stockDefinition = apiService.call(url);
            if (stockDefinition != null) {
                createStock(stockDefinition);
            } else {
                log.error("Incorrect url: " + url);
            }
        }
    }

    @Transactional
    @Override
    public Stock createStock(String stockDefinition) {
        Stock stock = stockMapper.toStock(stockDefinition);
        return save(stock);
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
