package com.github.onyshchenko.stock.data.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Data
@Getter
@Setter
public class Symbol {

    String symbol;
    String exchange;
    String name;
    Date date;
    String type;
    String iexId;
    String region;
    String currency;
    Boolean isEnabled;
    String figi;
    Long cik;

}


