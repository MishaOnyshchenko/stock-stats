package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Symbol;
import com.github.onyshchenko.stock.service.ApiService;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

@ExtendWith(value = {MockitoExtension.class})
class StockServiceImplTest {

    private static final String STOCK_URL = "https://sandbox.iexapis.com/stable/stock/MSFT/quote?token=Tpk_ee567917a6b640bb8602834c9d30e571";

    @Spy
    private ApiService apiService;
    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    void shouldGetNotEmptyQueue() {
        //GIVEN
        Symbol symbol = new Symbol();
        symbol.setSymbol("MOCKEDSYMBOL");
        Set<Symbol> symbols = new HashSet<>();
        symbols.add(symbol);
        //WHEN
        Queue<String> result = stockService.getUrls(symbols);
        //THEN
        assertThat(result, not(IsEmptyCollection.empty()));
    }

    @Test
    void shouldGetNotEmptyList() {
        //GIVEN
        Queue<String> urls = new ConcurrentLinkedQueue<>();
        urls.add(STOCK_URL);
        //WHEN
        List<String> result = stockService.getStockDefinitions(urls);
        //THEN
        assertThat(result, not(IsEmptyCollection.empty()));
    }
}