package com.xcode.homeworkback.domain.port;

import com.xcode.homeworkback.domain.model.Currency;

import java.util.List;

public interface CurrencyRepository {
    List<Currency> findAllCurrenciesExchangeRates();
}
