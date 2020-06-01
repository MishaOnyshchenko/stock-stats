package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Symbol;
import com.github.onyshchenko.stock.service.SymbolService;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

@ExtendWith(value = {MockitoExtension.class})
class UrlServiceImplTest {

    @Spy
    private SymbolService symbolService;

    @InjectMocks
    private UrlServiceImpl urlService;

    @Test
    void shouldGetNotEmptyQueue() {
        //GIVEN
        Symbol symbol = new Symbol();
        symbol.setSymbol("MOCKEDSYMBOL");
        Set<Symbol> symbols = new HashSet<>();
        symbols.add(symbol);
        //WHEN
        String mockedLine = "[{\"symbol\":\"INSU\",\"exchange\":\"ASN\",\"name\":\"iu spsqo aucAi rCsntsCarnAnloc.Iei \",\"date\":\"2020-05-30\",\"type\":\"cs\",\"iexId\":\"IEX_5331544832542D52\",\"region\":\"US\",\"currency\":\"USD\",\"isEnabled\":true,\"figi\":\"N0VHDBHB0G90\",\"cik\":\"1833510\"}]";
        //WHEN
        when(symbolService.getSymbols()).thenReturn(symbols);
        Queue<String> result = urlService.getUrls();
        //THEN
        assertThat(result, not(IsEmptyCollection.empty()));
    }
}