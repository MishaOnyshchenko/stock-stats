package com.github.onyshchenko.stock.service;

import com.github.onyshchenko.stock.data.domain.Symbol;

import java.util.Queue;
import java.util.Set;

public interface UrlService {

    Queue<String> getUrls(Set<Symbol> symbols);
}
