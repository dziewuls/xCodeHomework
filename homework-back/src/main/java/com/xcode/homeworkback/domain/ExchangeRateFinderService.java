package com.xcode.homeworkback.domain;

import com.xcode.homeworkback.domain.model.Currency;
import com.xcode.homeworkback.domain.port.CurrencyRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ExchangeRateFinderService {

    private CurrencyRepository currencyRepository;

    public Double findExchangeRate(String currency) {
        if (currency == null || currency.isEmpty()) return 0.0;

        List<Currency> currencies = this.currencyRepository.findAllCurrenciesExchangeRates();
        currencies.removeIf(c -> !currency.equalsIgnoreCase(c.getCurrencyCode()));

        return currencies.isEmpty() ? 0.0 : currencies.get(0).getValue();
    }
}
