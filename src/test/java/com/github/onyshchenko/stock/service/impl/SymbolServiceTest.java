package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Symbol;
import com.github.onyshchenko.stock.service.ApiService;
import com.github.onyshchenko.stock.utils.mappers.SymbolMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@ExtendWith(value = {MockitoExtension.class})
class SymbolServiceTest {

    private static final String REF_DATA_URL = "https://sandbox.iexapis.com/stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571";

    @Mock
    private ApiService apiService;
    @Spy
    private SymbolMapper symbolMapper;
    @InjectMocks
    private SymbolServiceImpl symbolService;


    @Test
    void shouldHaveOneElement() {
        //GIVEN
        String mockedLine = "[{\"symbol\":\"INSU\",\"exchange\":\"ASN\",\"name\":\"iu spsqo aucAi rCsntsCarnAnloc.Iei \",\"date\":\"2020-05-30\",\"type\":\"cs\",\"iexId\":\"IEX_5331544832542D52\",\"region\":\"US\",\"currency\":\"USD\",\"isEnabled\":true,\"figi\":\"N0VHDBHB0G90\",\"cik\":\"1833510\"}]";
        //WHEN
        when(apiService.call(REF_DATA_URL)).thenReturn(mockedLine);
        Set<Symbol> symbols = symbolService.getSymbols();
        //THEN
        assertThat(symbols, hasSize(1));
    }
}