package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Symbol;
import com.github.onyshchenko.stock.service.UrlService;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class UrlServiceImpl implements UrlService {

    @Override
    public Queue<String> getUrls(Set<Symbol> symbols) {
        BlockingQueue<String> urls = new LinkedBlockingQueue<>();
        for (Symbol symbol : symbols) {
            String stockUrl = String.format("https://sandbox.iexapis.com/stable/stock/%s/quote?token=Tpk_ee567917a6b640bb8602834c9d30e571", symbol.getSymbol());
            urls.add(stockUrl);
        }
        return urls;
    }
}
