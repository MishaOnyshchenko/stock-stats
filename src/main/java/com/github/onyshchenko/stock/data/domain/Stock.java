package com.github.onyshchenko.stock.data.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Stock {

    private String symbol;
    private String companyName;
    private String primaryExchange;
    private String calculationPrice;
    private Double open;
    private Double openTime;
    private String openSource;
    private Double close;
    private Double closeTime;
    private String closeSource;
    private Double high;
    private Double highTime;
    private String highSource;
    private Double low;
    private Double lowTime;
    private String lowSource;
    private Double latestPrice;
    private String latestSource;
    private String latestTime;
    private Double latestUpdate;
    private Double latestVolume;
    private String iexRealtimePrice;
    private String iexRealtimeSize;
    private String iexLastUpdated;
    private Double delayedPrice;
    private Double delayedPriceTime;
    private Double oddLotDelayedPrice;
    private Double oddLotDelayedPriceTime;
    private Double extendedPrice;
    private Double extendedChange;
    private Double extendedChangePercent;
    private Double extendedPriceTime;
    private Double previousClose;
    private Double previousVolume;
    private Double change;
    private Double changePercent;
    private Double volume;
    private String iexMarketPercent;
    private String iexVolume;
    private Double avgTotalVolume;
    private String iexBidPrice;
    private String iexBidSize;
    private String iexAskPrice;
    private String iexAskSize;
    private String iexOpen;
    private String iexOpenTime;
    private Double iexClose;
    private Double iexCloseTime;
    private Double marketCap;
    private Double peRatio;
    private Double week52High;
    private Double week52Low;
    private Double ytdChange;
    private Double lastTradeTime;
    private Boolean isUSMarketOpen;
}
