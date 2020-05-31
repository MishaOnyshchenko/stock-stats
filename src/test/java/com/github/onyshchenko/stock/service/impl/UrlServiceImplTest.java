package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Symbol;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

class UrlServiceImplTest {

    UrlServiceImpl urlService = new UrlServiceImpl();

    @Test
    void shouldGetNotEmptyQueue() {
        //GIVEN
        Symbol symbol = new Symbol();
        symbol.setSymbol("MOCKEDSYMBOL");
        Set<Symbol> symbols = new HashSet<>();
        symbols.add(symbol);
        //WHEN
        Queue<String> result = urlService.getUrls(symbols);
        //THEN
        assertThat(result, not(IsEmptyCollection.empty()));
    }
}