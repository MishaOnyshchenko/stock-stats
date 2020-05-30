package com.github.onyshchenko.stock.service.impl;

import com.github.onyshchenko.stock.service.ApiService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApiServiceImplTest {

    private static final String REF_DATA_URL = "https://sandbox.iexapis.com/stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571";
    private ApiService apiService = new ApiServiceImpl();

    @Test
    void shouldCallApi() {
        //GIVEN
        //WHEN
        String result = apiService.call(REF_DATA_URL);
        //THEN
        assertNotNull(result);
    }
}