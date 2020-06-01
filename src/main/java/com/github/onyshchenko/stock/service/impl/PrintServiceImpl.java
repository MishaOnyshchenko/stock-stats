package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Stock;
import com.github.onyshchenko.stock.service.PrintService;
import com.github.onyshchenko.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrintServiceImpl implements PrintService {

    @Autowired
    private StockService stockService;

    @Override
    public void printStats(int limit) {
        printHighestValues(limit);
        printGreatestChanges(limit);
    }

    private void printHighestValues(int limit) {
        List<Stock> stocks = stockService.getHighestValues(limit);
        System.out.println("-----------Highest values $----------");
        for (Stock stock : stocks) {
            System.out.println(String.format("%s - %s", stock.getClose(), stock.getCompanyName()));
        }
        System.out.println();
    }

    private void printGreatestChanges(int limit) {
        List<Stock> stocks = stockService.getGreatestChanges(limit);
        System.out.println("++++++++++Greatest changes %+++++++++");
        for (Stock stock : stocks) {
            System.out.println(String.format("%s - %s", stock.getChangePercent(), stock.getCompanyName()));
        }
        System.out.println("=====================================\n");
    }
}
