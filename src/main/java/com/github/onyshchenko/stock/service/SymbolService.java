package com.github.onyshchenko.stock.service;

import com.github.onyshchenko.stock.data.domain.Symbol;

import java.util.Set;

public interface SymbolService {

    Set<Symbol> getSymbols();
}
