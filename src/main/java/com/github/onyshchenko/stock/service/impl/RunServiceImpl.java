package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Symbol;
import com.github.onyshchenko.stock.service.RunService;
import com.github.onyshchenko.stock.service.StockService;
import com.github.onyshchenko.stock.service.SymbolService;
import com.github.onyshchenko.stock.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class RunServiceImpl implements RunService {

    @Autowired
    private SymbolService symbolService;

    @Autowired
    private UrlService urlService;

    @Autowired
    private StockService stockService;


    @Override
    public void runApp() {
        Set<Symbol> symbols = symbolService.getSymbols();
        Queue<String> urls = urlService.getUrls(symbols);

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(getPrintThread());
        executor.execute(getSaveThread(urls));
    }


    private Thread getSaveThread(Queue<String> urls) {
        return new Thread(() -> stockService.createStocks(urls));
    }


    private Thread getPrintThread() {
        return new Thread(() -> {
            int i = 1;
            while (true) {
                log.info("Get all stocks: " + i++);
                stockService.getAll();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
