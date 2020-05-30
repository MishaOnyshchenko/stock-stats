package com.github.onyshchenko.stock.utils.mappers;

import com.github.onyshchenko.stock.data.domain.Stock;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class StockMapperTest {

    private StockMapper mapper = new StockMapper();

    @Test
    void ShouldMapToStock() {
        //GIVEN
        String mockedLine = "{\n" +
                        "symbol: \"MSFT\",\n" +
                        "companyName: \"Microsoft Corp.\",\n" +
                        "primaryExchange: \"SAANDQ\",\n" +
                        "calculationPrice: \"close\",\n" +
                        "open: 186.48,\n" +
                        "openTime: 1640406005267,\n" +
                        "openSource: \"oflciifa\",\n" +
                        "close: 183.51,\n" +
                        "closeTime: 1653124475186,\n" +
                        "closeSource: \"liiofcaf\",\n" +
                        "high: 188.03,\n" +
                        "highTime: 1623608257311,\n" +
                        "highSource: \"eii pem1cltarden ed 5yu\",\n" +
                        "low: 185.54,\n" +
                        "lowTime: 1666780461484,\n" +
                        "lowSource: \"uil5eet1 ieam drpd necy\",\n" +
                        "latestPrice: 189.06,\n" +
                        "latestSource: \"Close\",\n" +
                        "latestTime: \"May 29, 2020\",\n" +
                        "latestUpdate: 1640072397002,\n" +
                        "latestVolume: 43574301,\n" +
                        "iexRealtimePrice: null,\n" +
                        "iexRealtimeSize: null,\n" +
                        "iexLastUpdated: null,\n" +
                        "delayedPrice: 184.7,\n" +
                        "delayedPriceTime: 1645864253676,\n" +
                        "oddLotDelayedPrice: 189.4,\n" +
                        "oddLotDelayedPriceTime: 1651954025147,\n" +
                        "extendedPrice: 191.5,\n" +
                        "extendedChange: 0.45,\n" +
                        "extendedChangePercent: 0.00258,\n" +
                        "extendedPriceTime: 1615478421089,\n" +
                        "previousClose: 182,\n" +
                        "previousVolume: 35411803,\n" +
                        "change: 1.88,\n" +
                        "changePercent: 0.0105,\n" +
                        "volume: 44174836,\n" +
                        "iexMarketPercent: null,\n" +
                        "iexVolume: null,\n" +
                        "avgTotalVolume: 37958542,\n" +
                        "iexBidPrice: null,\n" +
                        "iexBidSize: null,\n" +
                        "iexAskPrice: null,\n" +
                        "iexAskSize: null,\n" +
                        "iexOpen: null,\n" +
                        "iexOpenTime: null,\n" +
                        "iexClose: 192.937,\n" +
                        "iexCloseTime: 1596604588573,\n" +
                        "marketCap: 1448184820212,\n" +
                        "peRatio: 31.37,\n" +
                        "week52High: 196,\n" +
                        "week52Low: 121.66,\n" +
                        "ytdChange: 0.155544,\n" +
                        "lastTradeTime: 1657555009888,\n" +
                        "isUSMarketOpen: false\n" +
                        "}";
        //WHEN
        Stock result = mapper.toStock(mockedLine);
        //THEN
        assertThat(result.getCompanyName(), equalTo("Microsoft Corp."));
    }
}