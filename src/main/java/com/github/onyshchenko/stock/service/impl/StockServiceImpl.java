package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Stock;
import com.github.onyshchenko.stock.data.repository.CustomStockRepository;
import com.github.onyshchenko.stock.data.repository.StockRepository;
import com.github.onyshchenko.stock.service.ApiService;
import com.github.onyshchenko.stock.service.StockService;
import com.github.onyshchenko.stock.service.UrlService;
import com.github.onyshchenko.stock.utils.mappers.StockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private UrlService urlService;

    @Autowired
    private ApiService apiService;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CustomStockRepository customStockRepository;

    @Override
    public void createStocks(Queue<String> urls) {
        while (!urls.isEmpty()) {
            String url = urls.poll();
            String stockDefinition = apiService.call(url);
            if (stockDefinition != null) {
                save(createStock(stockDefinition));
            } else {
                log.error("Incorrect url: " + url);
            }
        }
        updateStocks();
    }


    @Transactional
    @Override
    public Stock createStock(String stockDefinition) {
        return stockMapper.toStock(stockDefinition);
    }

    @Transactional
    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Transactional
    @Override
    public void updateStock(Long id, Stock newStock) {
        Stock persist = stockRepository.getOne(id);

        persist.setOpen(newStock.getOpen());
        persist.setClose(newStock.getClose());
        persist.setChangePercent(newStock.getChangePercent());
    }

    @Override
    public List<Stock> getHighestValues(int limit) {
        return customStockRepository.findHighestValueOrderedByCloseAndCompanyName(limit);
    }

    @Override
    public List<Stock> getGreatestChanges(int limit) {
        return customStockRepository.findOrderedByGreatestChange(limit);
    }

    //TODO refactor later
    private void updateStocks() {
        List<Stock> savedStocks = stockRepository.findAll();
        Map<String, Stock> stocksByNames = savedStocks.stream().collect(Collectors.toMap(Stock::getCompanyName, Function.identity()));

        Queue<String> updatedUrls = urlService.getUrls();
        while (!updatedUrls.isEmpty()) {
            String url = updatedUrls.poll();

            String stockDefinition = apiService.call(url);
            if (stockDefinition != null) {
                Stock newStock = createStock(stockDefinition);
                checkIfExist(stocksByNames, newStock);
            } else {
                log.error("Incorrect url: " + url);
            }
        }
    }

    //TODO refactor later
    private void checkIfExist(Map<String, Stock> stocksByNames, Stock newStock){
        if (stocksByNames.containsKey(newStock.getCompanyName())) {
            Stock saved = stocksByNames.get(newStock.getCompanyName());
            updateIfChanged(newStock, saved);
        } else {
            save(newStock);
        }
    }

    //TODO refactor later
    private void updateIfChanged(Stock newStock, Stock saved){
        if (newStock.getClose() != saved.getClose()) {
            Stock persist = stockRepository.getOne(saved.getId());
            persist.setClose(newStock.getClose());
            updateStock(saved.getId(), newStock);
        }
    }

}
