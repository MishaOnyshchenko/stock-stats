package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.data.domain.Symbol;
import com.github.onyshchenko.stock.service.ApiService;
import com.github.onyshchenko.stock.service.SymbolService;
import com.github.onyshchenko.stock.utils.mappers.SymbolMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class SymbolServiceImpl implements SymbolService {

    private static final String REF_DATA_URL = "https://sandbox.iexapis.com/stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571";

    @Autowired
    private ApiService apiService;

    @Autowired
    private SymbolMapper symbolMapper;


    @Override
    public Set<Symbol> getSymbols() {
        String allSymbolsInLine = apiService.call(REF_DATA_URL);
        return symbolMapper.toSymbols(allSymbolsInLine);
    }
}
