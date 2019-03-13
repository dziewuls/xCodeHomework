package com.xcode.homeworkback.domain;

import com.xcode.homeworkback.domain.port.CurrencyRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExchangeRateFinderService {

    private CurrencyRepository currencyRepository;

    public Double findExchangeRate(String currency) {
        return null;
    }
}
