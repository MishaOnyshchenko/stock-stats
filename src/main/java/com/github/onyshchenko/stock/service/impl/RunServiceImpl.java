package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.service.PrintService;
import com.github.onyshchenko.stock.service.RunService;
import com.github.onyshchenko.stock.service.StockService;
import com.github.onyshchenko.stock.service.UrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
public class RunServiceImpl implements RunService {

    @Autowired
    private UrlService urlService;

    @Autowired
    private StockService stockService;

    @Autowired
    private PrintService printService;


    @Override
    public void runApp() {
        Queue<String> urls = urlService.getUrls();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(getPrintThread());
        executor.execute(getSaveThread(urls));
        executor.shutdown();
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
