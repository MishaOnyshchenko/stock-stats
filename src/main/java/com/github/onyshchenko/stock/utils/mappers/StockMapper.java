package com.github.onyshchenko.stock.utils.mappers;

import com.github.onyshchenko.stock.data.domain.Stock;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StockMapper {

    public Stock toStock(String json) {
        Stock stock = new Gson().fromJson(json, Stock.class);
        if (stock == null) {
            log.error("Error while mapping Symbol set from json: " + json);
        }
        return stock;
    }
}