package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.service.ApiService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(value = {MockitoExtension.class})
class StockServiceImplTest {

    private static final String STOCK_URL = "https://sandbox.iexapis.com/stable/stock/MSFT/quote?token=Tpk_ee567917a6b640bb8602834c9d30e571";

    @Spy
    private ApiService apiService;
    @InjectMocks
    private StockServiceImpl stockService;

    //TODO refactor tests later
//    @Test
//    void shouldGetNotEmptyList() {
//        //GIVEN
//        Queue<String> urls = new ConcurrentLinkedQueue<>();
//        urls.add(STOCK_URL);
//        //WHEN
//        List<String> result = stockService.getStockDefinitions(urls);
//        //THEN
//        assertThat(result, not(IsEmptyCollection.empty()));
//}
}