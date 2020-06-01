package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Symbol;
import com.github.onyshchenko.stock.service.*;
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

    @Autowired
    private PrintService printService;


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
            while (true) {
                printService.printStats(5);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
