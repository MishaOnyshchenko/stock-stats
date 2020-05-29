package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Symbol;
import com.github.onyshchenko.stock.service.ApiService;
import com.github.onyshchenko.stock.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class StockServiceImpl implements StockService {

    private static final String STOCK_URL_PREFIX = "https://sandbox.iexapis.com/stable/stock/";
    private static final String STOCK_URL_SUFFIX = "/quote?token=Tpk_ee567917a6b640bb8602834c9d30e571";

    @Autowired
    private ApiService apiService;

    @Override
    public void getStocks(Set<Symbol> symbols) {
        for (Symbol symbol : symbols) {
            String stockUrl = String.format("https://sandbox.iexapis.com/stable/stock/%s/quote?token=Tpk_ee567917a6b640bb8602834c9d30e571", symbol.getSymbol());
            String stockInfo = apiService.call(stockUrl);
            log.info(stockInfo);
        }
    }
}
