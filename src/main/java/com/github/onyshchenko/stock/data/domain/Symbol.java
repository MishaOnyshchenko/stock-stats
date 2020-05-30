package com.github.onyshchenko.stock.data.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Data
@Getter
@Setter
public class Symbol {

    private String symbol;
    private String exchange;
    private String name;
    private Date date;
    private String type;
    private String iexId;
    private String region;
    private String currency;
    private Boolean isEnabled;
    private String figi;
    private Long cik;

}


